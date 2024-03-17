package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotBlank;

public record AnimalTypesDTO(
  @NotBlank String name) {
}
