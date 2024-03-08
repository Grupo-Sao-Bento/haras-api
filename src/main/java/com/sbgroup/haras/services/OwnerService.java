package com.sbgroup.haras.services;

import com.sbgroup.haras.dtos.OwnerRecordDTO;
import com.sbgroup.haras.models.OwnerModel;
import com.sbgroup.haras.repositories.OwnerRepository;
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
public class OwnerService {

    @Autowired()
    OwnerRepository ownerRepository;

    @Transactional()
    public ResponseEntity<OwnerModel> saveOwner(OwnerRecordDTO newOwnerDto) {
        var newOwnerModel = new OwnerModel();
        BeanUtils.copyProperties(newOwnerDto, newOwnerModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerRepository.save(newOwnerModel));
    }

    public ResponseEntity<List<OwnerModel>> getAllOwners() {
        return ResponseEntity.status(HttpStatus.OK).body(ownerRepository.findAll());
    }

    public ResponseEntity<Object> getOwnerById(UUID ownerId) {
        Optional<OwnerModel> ownerModel = ownerRepository.findById(ownerId);

        if (ownerModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Owner id not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(ownerModel);
    }

    @Transactional()
    public ResponseEntity<Object> updateOwnerById(OwnerRecordDTO ownerDto, UUID ownerId) {
        Optional<OwnerModel> ownerModel = ownerRepository.findById(ownerId);

        if (ownerModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Owner id not found");
        }

        var updatedOwner = ownerModel.get();
        BeanUtils.copyProperties(ownerDto, updatedOwner);
        return ResponseEntity.status(HttpStatus.OK).body(ownerRepository.save(updatedOwner));
    }

    @Transactional()
    public ResponseEntity<Object> deleteOwnerById(UUID ownerId) {
        Optional<OwnerModel> ownerModel = ownerRepository.findById(ownerId);

        if (ownerModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Owner id not found");
        }

        ownerRepository.delete(ownerModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Owner deleted successfuly");
    }
}
