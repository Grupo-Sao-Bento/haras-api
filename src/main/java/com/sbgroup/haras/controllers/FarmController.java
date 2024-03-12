package com.sbgroup.haras.controllers;

import com.sbgroup.haras.dtos.FarmRecordDTO;
import com.sbgroup.haras.models.FarmModel;
import com.sbgroup.haras.services.FarmService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/farms")
public class FarmController {

    @Autowired()
    FarmService farmService;

    @PostMapping()
    public ResponseEntity<FarmModel> saveFarm(@RequestBody @Valid FarmRecordDTO newFarmDto) {
        return farmService.saveFarm(newFarmDto);
    }

    @GetMapping()
    public ResponseEntity<List<FarmModel>> getAllFarms() {
        return farmService.getAllFarms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFarmById(@PathVariable(value = "id") UUID farmId) {
        return farmService.getFarmById(farmId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFarmById(@RequestBody @Valid FarmRecordDTO farmDto,
                                                  @PathVariable(value = "id") UUID farmId) {
        return farmService.updateFarmById(farmDto, farmId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFarmById(@PathVariable(value = "id") UUID farmId) {
        return farmService.deleteFarmById(farmId);
    }
}
