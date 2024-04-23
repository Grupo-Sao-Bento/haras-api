package com.sbgroup.haras.controllers;

import com.sbgroup.haras.dtos.AnimalDTO;
import com.sbgroup.haras.models.Animal;
import com.sbgroup.haras.models.User;
import com.sbgroup.haras.services.AnimalService;

import com.sbgroup.haras.services.UserService;
import com.sbgroup.haras.utils.PaginatedResponseUtil;
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
  public ResponseEntity<Object> register(@RequestBody @Valid AnimalDTO animalDTO, HttpServletRequest request) {
    String token = request.getHeader("Authorization").replace("Bearer ", "");
    Optional<User> user = userService.getUserByToken(token);

    User userModel = user.get();

    return ResponseEntity.status(HttpStatus.OK).body(animalService.registerAnimal(animalDTO, userModel));
  }

  @GetMapping()
  public ResponseEntity<PaginatedResponseUtil<Animal>> getAllAnimals(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    List<Animal> animals = animalService.getAllAnimals(page, size);

    PaginatedResponseUtil<Animal> paginatedResponse = new PaginatedResponseUtil<>(
        animals,
        page,
        animalService.getAnimalsTotalPages(size),
        animalService.getAnimalsTotalElements());

    return ResponseEntity.status(HttpStatus.OK).body(paginatedResponse);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<Object> getAnimalsByName(@PathVariable(value = "name") String animalName) {
    List<Animal> animals = animalService.getAnimalsByName(animalName);

    return ResponseEntity.status(HttpStatus.OK).body(animals);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getAnimalById(@PathVariable(value = "id") UUID animalId) {
    Optional<Animal> animalModel = animalService.getAnimalById(animalId);

    if (animalModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal id not found");
    }

    return ResponseEntity.status(HttpStatus.OK).body(animalModel);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateAnimalById(@RequestBody @Valid AnimalDTO animalDto,
      @PathVariable(value = "id") UUID animalId, HttpServletRequest request) {
    String token = request.getHeader("Authorization").replace("Bearer ", "");
    Optional<User> user = userService.getUserByToken(token);

    User userModel = user.get();
    Optional<Animal> animalModel = animalService.updateAnimalById(animalDto, animalId, userModel);

    if (animalModel.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal id not found");
    }

    return ResponseEntity.status(HttpStatus.OK).body(animalModel);

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
