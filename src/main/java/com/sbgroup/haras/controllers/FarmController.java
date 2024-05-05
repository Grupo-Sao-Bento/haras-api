package com.sbgroup.haras.controllers;

import com.sbgroup.haras.dtos.FarmDTO;
import com.sbgroup.haras.models.Farm;
import com.sbgroup.haras.services.FarmService;
import com.sbgroup.haras.utils.PaginatedResponseUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/farms")
public class FarmController {
  
  @Autowired()
  private FarmService farmService;

  @PostMapping()
  public ResponseEntity<Object> register(@RequestBody @Valid FarmDTO farmDTO, HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.OK).body(farmService.registerFarm(farmDTO));
  }

  @GetMapping()
  public ResponseEntity<PaginatedResponseUtil<Farm>> getAllFarms(
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size ) {
    List<Farm> farms = farmService.getAllFarms(page, size);

    PaginatedResponseUtil<Farm> paginatedResponse = new PaginatedResponseUtil<>(
      farms,
      page,
      farmService.getFarmsTotalPages(size),
      farmService.getFarmsTotalElements()
    );

    return ResponseEntity.status(HttpStatus.OK).body(paginatedResponse);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<Object> getFarmsByName(@PathVariable(value = "name") String farmName) {
    List<Farm> farms = farmService.getFarmsByName(farmName);

    return ResponseEntity.status(HttpStatus.OK).body(farms);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getFarmById(@PathVariable(value = "id") UUID farmId) {
    Optional<Farm> farmModel = farmService.getFarmById(farmId);

    if (farmModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farm id not found");
    }

    return ResponseEntity.status(HttpStatus.OK).body(farmModel);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateFarmById(@RequestBody @Valid FarmDTO farmDto,
                                          @PathVariable(value = "id") UUID farmId) {
      Optional<Farm> farmModel = farmService.updateFarmById(farmDto, farmId);

      if (farmModel.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farm id not found");
      }

      return ResponseEntity.status(HttpStatus.OK).body(farmModel);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteFarmById(@PathVariable(value = "id") UUID farmId) {
    Optional<Farm> farmModel = farmService.deleteFarmById(farmId);

    if (farmModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farm id not found");
    }

    return ResponseEntity.status(HttpStatus.OK).body("Farm deleted successfully");
  }

}
