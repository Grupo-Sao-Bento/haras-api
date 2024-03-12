package com.sbgroup.haras.services;

import com.sbgroup.haras.dtos.HorseRecordDTO;
import com.sbgroup.haras.models.HorseModel;
import com.sbgroup.haras.repositories.HorseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HorseService {
  
  @Autowired()
  HorseRepository horseRepository;
  
  @Transactional()
  public ResponseEntity<HorseModel> saveHorse(HorseRecordDTO newHorseDto) {
    var newHorseModel = new HorseModel();
    BeanUtils.copyProperties(newHorseDto, newHorseModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(horseRepository.save(newHorseModel));
  }
  
  public ResponseEntity<List<HorseModel>> getAllHorses() {
    return ResponseEntity.status(HttpStatus.OK).body(horseRepository.findAll());
  }
  
  public ResponseEntity<Object> getHorseById(UUID horseId) {
    Optional<HorseModel> horseModel = horseRepository.findById(horseId);
    
    if (horseModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horse id not found");
    }
    
    return ResponseEntity.status(HttpStatus.OK).body(horseModel);
  }
  
  @Transactional()
  public ResponseEntity<Object> updateHorseById(HorseRecordDTO horseDto, UUID horseId) {
    Optional<HorseModel> horseModel = horseRepository.findById(horseId);
    
    if (horseModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horse id not found");
    }
    
    var updatedHorse = horseModel.get();
    BeanUtils.copyProperties(horseDto, updatedHorse);
    return ResponseEntity.status(HttpStatus.OK).body(horseRepository.save(updatedHorse));
  }
  
  @Transactional()
  public ResponseEntity<Object> deleteHorseById(UUID horseId) {
    Optional<HorseModel> horseModel = horseRepository.findById(horseId);
    
    if (horseModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horse id not found");
    }
    
    horseRepository.delete(horseModel.get());
    return ResponseEntity.status(HttpStatus.OK).body("Horse deleted successfuly");
  }
}
