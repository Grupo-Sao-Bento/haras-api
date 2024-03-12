package com.sbgroup.haras.controllers;

import com.sbgroup.haras.dtos.StayRecordDTO;
import com.sbgroup.haras.models.StayModel;
import com.sbgroup.haras.services.StayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stays")
public class StayController {

    @Autowired()
    StayService stayService;

    @PostMapping()
    public ResponseEntity<StayModel> saveStay(@RequestBody @Valid StayRecordDTO newStayDto) {
        return stayService.saveStay(newStayDto);
    }

    @GetMapping()
    public ResponseEntity<List<StayModel>> getAllStays() {
        return stayService.getAllStays();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStayById(@PathVariable(value = "id") UUID stayId) {
        return stayService.getStayById(stayId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStayById(@RequestBody @Valid StayRecordDTO stayDto,
                                                  @PathVariable(value = "id") UUID stayId) {
        return stayService.updateStayById(stayDto, stayId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStayById(@PathVariable(value = "id") UUID stayId) {
        return stayService.deleteStayById(stayId);
    }
}
