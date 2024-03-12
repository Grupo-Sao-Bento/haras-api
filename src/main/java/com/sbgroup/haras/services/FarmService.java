package com.sbgroup.haras.services;

import com.sbgroup.haras.dtos.FarmRecordDTO;
import com.sbgroup.haras.models.FarmModel;
import com.sbgroup.haras.repositories.FarmRepository;
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
public class FarmService {
  
  @Autowired()
  FarmRepository farmRepository;
  
  @Transactional()
  public ResponseEntity<FarmModel> saveFarm(FarmRecordDTO newFarmDto) {
    var newFarmModel = new FarmModel();
    BeanUtils.copyProperties(newFarmDto, newFarmModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(farmRepository.save(newFarmModel));
  }
  
  public ResponseEntity<List<FarmModel>> getAllFarms() {
    return ResponseEntity.status(HttpStatus.OK).body(farmRepository.findAll());
  }
  
  public ResponseEntity<Object> getFarmById(UUID farmId) {
    Optional<FarmModel> farmModel = farmRepository.findById(farmId);
    
    if (farmModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farm id not found");
    }
    
    return ResponseEntity.status(HttpStatus.OK).body(farmModel);
  }
  
  @Transactional()
  public ResponseEntity<Object> updateFarmById(FarmRecordDTO farmDto, UUID farmId) {
    Optional<FarmModel> farmModel = farmRepository.findById(farmId);
    
    if (farmModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farm id not found");
    }
    
    var updatedFarm = farmModel.get();
    BeanUtils.copyProperties(farmDto, updatedFarm);
    return ResponseEntity.status(HttpStatus.OK).body(farmRepository.save(updatedFarm));
  }
  
  @Transactional()
  public ResponseEntity<Object> deleteFarmById(UUID farmId) {
    Optional<FarmModel> farmModel = farmRepository.findById(farmId);
    
    if (farmModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farm id not found");
    }
    
    farmRepository.delete(farmModel.get());
    return ResponseEntity.status(HttpStatus.OK).body("Farm deleted successfuly");
  }
}
