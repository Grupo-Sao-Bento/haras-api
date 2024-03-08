package com.sbgroup.haras.controllers;

import com.sbgroup.haras.dtos.OwnerRecordDTO;
import com.sbgroup.haras.models.OwnerModel;
import com.sbgroup.haras.services.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired()
    OwnerService ownerService;

    @PostMapping()
    public ResponseEntity<OwnerModel> saveOwner(@RequestBody @Valid OwnerRecordDTO newOwnerDto) {
        return ownerService.saveOwner(newOwnerDto);
    }

    @GetMapping()
    public ResponseEntity<List<OwnerModel>> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getOwnerById(@PathVariable(value = "id") UUID ownerId) {
        return ownerService.getOwnerById(ownerId);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Object> updateOwnerById(@RequestBody @Valid OwnerRecordDTO ownerDto,
                                                  @PathVariable(value = "id") UUID ownerId) {
        return ownerService.updateOwnerById(ownerDto, ownerId);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Object> deleteOwnerById(@PathVariable(value = "id") UUID ownerId) {
        return ownerService.deleteOwnerById(ownerId);
    }
}
