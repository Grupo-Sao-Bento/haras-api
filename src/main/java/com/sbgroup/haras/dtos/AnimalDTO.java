package com.sbgroup.haras.dtos;

import com.sbgroup.haras.enums.AnimalCoat;
import com.sbgroup.haras.enums.AnimalGender;
import com.sbgroup.haras.enums.AnimalType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record AnimalDTO(
  @NotBlank String name,
  @NotNull AnimalType type,
  @NotNull AnimalGender gender,
  @NotNull AnimalCoat coat,
  Timestamp birthDate,
  String registry,
  String owner,
  boolean isAlive,
  double dailyFee
) {}
