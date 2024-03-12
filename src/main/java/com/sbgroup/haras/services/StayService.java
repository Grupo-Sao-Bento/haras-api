package com.sbgroup.haras.services;

import com.sbgroup.haras.dtos.StayRecordDTO;
import com.sbgroup.haras.models.StayModel;
import com.sbgroup.haras.repositories.StayRepository;
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
public class StayService {
  
  @Autowired()
  StayRepository stayRepository;
  
  @Transactional()
  public ResponseEntity<StayModel> saveStay(StayRecordDTO newStayDto) {
    var newStayModel = new StayModel();
    BeanUtils.copyProperties(newStayDto, newStayModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(stayRepository.save(newStayModel));
  }
  
  public ResponseEntity<List<StayModel>> getAllStays() {
    return ResponseEntity.status(HttpStatus.OK).body(stayRepository.findAll());
  }
  
  public ResponseEntity<Object> getStayById(UUID stayId) {
    Optional<StayModel> stayModel = stayRepository.findById(stayId);
    
    if (stayModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stay id not found");
    }
    
    return ResponseEntity.status(HttpStatus.OK).body(stayModel);
  }
  
  @Transactional()
  public ResponseEntity<Object> updateStayById(StayRecordDTO stayDto, UUID stayId) {
    Optional<StayModel> stayModel = stayRepository.findById(stayId);
    
    if (stayModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stay id not found");
    }
    
    var updatedStay = stayModel.get();
    BeanUtils.copyProperties(stayDto, updatedStay);
    return ResponseEntity.status(HttpStatus.OK).body(stayRepository.save(updatedStay));
  }
  
  @Transactional()
  public ResponseEntity<Object> deleteStayById(UUID stayId) {
    Optional<StayModel> stayModel = stayRepository.findById(stayId);
    
    if (stayModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stay id not found");
    }
    
    stayRepository.delete(stayModel.get());
    return ResponseEntity.status(HttpStatus.OK).body("Stay deleted successfuly");
  }
}
