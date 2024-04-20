package com.sbgroup.haras.services;

import com.sbgroup.haras.dtos.EventDTO;
import com.sbgroup.haras.models.Event;
import com.sbgroup.haras.repositories.EventRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(EventDTO eventDTO) {
        var newEvent = new Event();
        BeanUtils.copyProperties(eventDTO, newEvent);

        return eventRepository.save(newEvent);
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public void cancelEvent(UUID id) {
        eventRepository.deleteById(id);
    }
}
