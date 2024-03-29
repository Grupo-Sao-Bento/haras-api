package com.sbgroup.haras.dtos;

import com.sbgroup.haras.enums.AnimalCoat;
import com.sbgroup.haras.enums.AnimalGender;
import com.sbgroup.haras.enums.AnimalType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnimalDTO(
  @NotBlank String name,
  @NotNull AnimalType type,
  @NotNull AnimalGender gender,
  @NotNull AnimalCoat coat ) {
}
