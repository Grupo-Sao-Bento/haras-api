package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotBlank;

public record AnimalTypesRecordDTO(
  @NotBlank String name) {
}
