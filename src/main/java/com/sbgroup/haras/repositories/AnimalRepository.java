package com.sbgroup.haras.repositories;

import com.sbgroup.haras.models.Animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, UUID> {

  List<Animal> findAllByName(String name);

}
