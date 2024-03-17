package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotBlank;

public record GroupDTO(
  @NotBlank String name,
  @NotBlank String farmId) {
}
