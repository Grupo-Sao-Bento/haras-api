package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotBlank;

public record ClientDTO(
  @NotBlank String name) {
}
