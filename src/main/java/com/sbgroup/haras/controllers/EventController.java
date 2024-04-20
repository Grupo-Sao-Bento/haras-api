package com.sbgroup.haras.controllers;

import com.sbgroup.haras.dtos.EventDTO;
import com.sbgroup.haras.models.Event;
import com.sbgroup.haras.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> getEvents() {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getEvents());
    }

    @PostMapping
    public ResponseEntity createEvent(@RequestBody EventDTO eventDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.createEvent(eventDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity cancelEvent(@PathVariable() UUID id) {
        eventService.cancelEvent(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
