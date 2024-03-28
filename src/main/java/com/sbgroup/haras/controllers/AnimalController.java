package com.sbgroup.haras.controllers;

import com.sbgroup.haras.dtos.AnimalDTO;
import com.sbgroup.haras.models.Animal;
import com.sbgroup.haras.services.AnimalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/animals")
public class AnimalController {
  
  @Autowired()
  private AnimalService animalService;

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody @Valid AnimalDTO data) {
      return ResponseEntity.status(HttpStatus.OK).body(animalService.registerAnimal(data));
  }

  @GetMapping()
  public ResponseEntity<List<Animal>> getAllAnimals() {
    return ResponseEntity.status(HttpStatus.OK).body(animalService.getAllAnimals());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getAnimalById(@PathVariable(value = "id") UUID animalId) {
    Optional<Animal> animalModel = animalService.getAnimalById(animalId);

    if (animalModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal id not found");
    }

    return ResponseEntity.status(HttpStatus.OK).body(animalModel);
  }

  // Get by name (list)

  // Update

  // Delete
}
