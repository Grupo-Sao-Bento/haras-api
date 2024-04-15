package com.sbgroup.haras.services;

import com.sbgroup.haras.dtos.FarmDTO;
import com.sbgroup.haras.models.Farm;
import com.sbgroup.haras.repositories.FarmRepository;
import com.sbgroup.haras.utils.TimeUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FarmService {
  
  @Autowired()
  private FarmRepository farmRepository;
  
  @Transactional()
  public Farm registerFarm(FarmDTO farmDTO) {
    var newFarm = new Farm();
    
    BeanUtils.copyProperties(farmDTO, newFarm);
    newFarm.setCreatedAt(TimeUtil.getCurrentTimestamp());
    
    return farmRepository.save(newFarm);
  }
  
  public List<Farm> getAllFarms(int page, int size) {
    Sort sort = Sort.by(Sort.Direction.ASC, "name");
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Farm> farmsPage = farmRepository.findAll(pageable);
    
    return farmsPage.getContent();
  }
  
  public long getFarmsTotalElements() {
    return farmRepository.count();
  }
  
  public int getFarmsTotalPages(int size) {
    Pageable pageable = PageRequest.of(0, size);
    long totalElements = getFarmsTotalElements();
    
    return (int) Math.ceil((double) totalElements / (double) size);
  }
  
  public List<Farm> getFarmsByName(String farmName) {
    return farmRepository.findAllByName(farmName);
  }
  
  public Optional<Farm> getFarmById(UUID farmId) {
    return farmRepository.findById(farmId);
  }
  
  @Transactional()
  public Optional<Farm> updateFarmById(FarmDTO farmDto, UUID farmId) {
    var farmModel = farmRepository.findById(farmId);
    
    if (farmModel.isEmpty()) {
      return Optional.empty();
    }
    
    var updatedFarm = farmModel.get();
    BeanUtils.copyProperties(farmDto, updatedFarm);
    
    return Optional.of(farmRepository.save(updatedFarm));
  }
  
  @Transactional()
  public Optional<Farm> deleteFarmById(UUID farmId) {
    var farmModel = farmRepository.findById(farmId);
    
    if (farmModel.isEmpty()) {
      return Optional.empty();
    }
    
    farmRepository.delete(farmModel.get());
    
    return farmModel;
  }
  
}
