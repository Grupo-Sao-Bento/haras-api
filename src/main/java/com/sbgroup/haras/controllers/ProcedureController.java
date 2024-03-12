package com.sbgroup.haras.controllers;

import com.sbgroup.haras.dtos.ProcedureRecordDTO;
import com.sbgroup.haras.models.ProcedureModel;
import com.sbgroup.haras.services.ProcedureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/procedures")
public class ProcedureController {
  
  @Autowired()
  ProcedureService procedureService;
  
  @PostMapping()
  public ResponseEntity<ProcedureModel> saveProcedure(@RequestBody @Valid ProcedureRecordDTO newProcedureDto) {
    return procedureService.saveProcedure(newProcedureDto);
  }
  
  @GetMapping()
  public ResponseEntity<List<ProcedureModel>> getAllProcedures() {
    return procedureService.getAllProcedures();
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<Object> getProcedureById(@PathVariable(value = "id") UUID procedureId) {
    return procedureService.getProcedureById(procedureId);
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateProcedureById(@RequestBody @Valid ProcedureRecordDTO procedureDto,
                                                    @PathVariable(value = "id") UUID procedureId) {
    return procedureService.updateProcedureById(procedureDto, procedureId);
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteProcedureById(@PathVariable(value = "id") UUID procedureId) {
    return procedureService.deleteProcedureById(procedureId);
  }
}
