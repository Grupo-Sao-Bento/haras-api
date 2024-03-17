package com.sbgroup.haras.services;

import com.sbgroup.haras.dtos.ProcedureDTO;
import com.sbgroup.haras.models.ProcedureModel;
import com.sbgroup.haras.repositories.ProcedureRepository;
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
public class ProcedureService {
  
  @Autowired()
  ProcedureRepository procedureRepository;
  
  @Transactional()
  public ResponseEntity<ProcedureModel> saveProcedure(ProcedureDTO newProcedureDto) {
    var newProcedureModel = new ProcedureModel();
    BeanUtils.copyProperties(newProcedureDto, newProcedureModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(procedureRepository.save(newProcedureModel));
  }
  
  public ResponseEntity<List<ProcedureModel>> getAllProcedures() {
    return ResponseEntity.status(HttpStatus.OK).body(procedureRepository.findAll());
  }
  
  public ResponseEntity<Object> getProcedureById(UUID procedureId) {
    Optional<ProcedureModel> procedureModel = procedureRepository.findById(procedureId);
    
    if (procedureModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Procedure id not found");
    }
    
    return ResponseEntity.status(HttpStatus.OK).body(procedureModel);
  }
  
  @Transactional()
  public ResponseEntity<Object> updateProcedureById(ProcedureDTO procedureDto, UUID procedureId) {
    Optional<ProcedureModel> procedureModel = procedureRepository.findById(procedureId);
    
    if (procedureModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Procedure id not found");
    }
    
    var updatedProcedure = procedureModel.get();
    BeanUtils.copyProperties(procedureDto, updatedProcedure);
    return ResponseEntity.status(HttpStatus.OK).body(procedureRepository.save(updatedProcedure));
  }
  
  @Transactional()
  public ResponseEntity<Object> deleteProcedureById(UUID procedureId) {
    Optional<ProcedureModel> procedureModel = procedureRepository.findById(procedureId);
    
    if (procedureModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Procedure id not found");
    }
    
    procedureRepository.delete(procedureModel.get());
    return ResponseEntity.status(HttpStatus.OK).body("Procedure deleted successfuly");
  }
}
