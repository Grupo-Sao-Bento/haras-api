package com.sbgroup.haras.controllers;

import com.sbgroup.haras.dtos.AnimalDTO;
import com.sbgroup.haras.dtos.AnimalDTO;
import com.sbgroup.haras.models.Animal;
import com.sbgroup.haras.models.Animal;
import com.sbgroup.haras.models.User;
import com.sbgroup.haras.services.AnimalService;

import com.sbgroup.haras.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
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

  @Autowired()
  private UserService userService;

  @PostMapping()
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

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateAnimalById(@RequestBody @Valid AnimalDTO animalDto,
                   @PathVariable(value = "id") UUID animalId, HttpServletRequest request) {
    String token = request.getHeader("Authorization").replace("Bearer ", "");
    Optional<User> user = userService.getUserByToken(token);

    if (user.isPresent()) {
      User userModel = user.get();
      Optional<Animal> animalModel = animalService.updateAnimalById(animalDto, animalId, userModel);

      if (animalModel.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal id not found");
      }

      return ResponseEntity.status(HttpStatus.OK).body(animalModel);

    } else {
      throw new RuntimeException("User identity error");
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteAnimalById(@PathVariable(value = "id") UUID animalId) {
    Optional<Animal> animalModel = animalService.deleteAnimalById(animalId);

    if (animalModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal id not found");
    }

    return ResponseEntity.status(HttpStatus.OK).body("Animal deleted successfuly");
  }
}
