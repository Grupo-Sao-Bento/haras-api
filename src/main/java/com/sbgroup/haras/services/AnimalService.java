package com.sbgroup.haras.services;

import com.sbgroup.haras.dtos.AnimalDTO;
import com.sbgroup.haras.models.Animal;
import com.sbgroup.haras.models.User;
import com.sbgroup.haras.repositories.AnimalRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnimalService {
  
  @Autowired()
  private AnimalRepository animalRepository;

  @Transactional()
  public Animal registerAnimal(AnimalDTO animalDTO) {
    Timestamp now = Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.of("-03:00")));

    var newAnimal = new Animal();

    BeanUtils.copyProperties(animalDTO, newAnimal);
    newAnimal.setCreatedAt(now);

    return animalRepository.save(newAnimal);
  }

  public List<Animal> getAllAnimals() {
    return animalRepository.findAll();
  }

  public List<Animal> getAnimalsByName(String animalName) {
    return animalRepository.findAllByName(animalName);
  }
  
  public Optional<Animal> getAnimalById(UUID animalId) {
    return animalRepository.findById(animalId);
  }

  @Transactional()
  public Optional<Animal> updateAnimalById(AnimalDTO animalDto, UUID animalId, User authUser) {
    var animalModel = animalRepository.findById(animalId);

    if (animalModel.isEmpty()) {
      return Optional.empty();
    }

    Timestamp now = Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.of("-03:00")));
    var updatedAnimal = animalModel.get();
    BeanUtils.copyProperties(animalDto, updatedAnimal);
    updatedAnimal.setUpdatedAt(now);
    updatedAnimal.setUpdatedBy(authUser);

    return Optional.of(animalRepository.save(updatedAnimal));
  }

  @Transactional()
  public Optional<Animal> deleteAnimalById(UUID animalId) {
    var animalModel = animalRepository.findById(animalId);

    if (animalModel.isEmpty()) {
      return Optional.empty();
    }

    animalRepository.delete(animalModel.get());
    return animalModel;
  }
}
