package com.sbgroup.haras.controllers;

import com.sbgroup.haras.dtos.HorseRecordDTO;
import com.sbgroup.haras.models.HorseModel;
import com.sbgroup.haras.services.HorseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/horses")
public class HorseController {
  
  @Autowired()
  HorseService horseService;
  
  @PostMapping()
  public ResponseEntity<HorseModel> saveHorse(@RequestBody @Valid HorseRecordDTO newHorseDto) {
    return horseService.saveHorse(newHorseDto);
  }
  
  @GetMapping()
  public ResponseEntity<List<HorseModel>> getAllHorses() {
    return horseService.getAllHorses();
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Object> getHorseById(@PathVariable(value = "id") UUID horseId) {
    return horseService.getHorseById(horseId);
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateHorseById(@RequestBody @Valid HorseRecordDTO horseDto,
                                                @PathVariable(value = "id") UUID horseId) {
    return horseService.updateHorseById(horseDto, horseId);
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteHorseById(@PathVariable(value = "id") UUID horseId) {
    return horseService.deleteHorseById(horseId);
  }
}
