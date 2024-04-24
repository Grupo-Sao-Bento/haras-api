package com.sbgroup.haras.dtos;

import com.sbgroup.haras.enums.AnimalBreed;
import com.sbgroup.haras.enums.AnimalCoat;
import com.sbgroup.haras.enums.AnimalGender;
import com.sbgroup.haras.enums.AnimalType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.UUID;

public record AnimalDTO(
  @NotBlank String name,
  @NotNull AnimalType type,
  @NotNull AnimalGender gender,
  @NotNull AnimalCoat coat,
  Timestamp birthDate,
  String registry,
  UUID owner,
  boolean isAlive,
  double dailyFee,
  AnimalBreed breed,
  UUID father,
  UUID mother
) {}
