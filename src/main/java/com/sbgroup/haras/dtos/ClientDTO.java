package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotBlank;

public record ClientDTO(
  @NotBlank String firstName,
  @NotBlank String lastName,
  @NotBlank String phoneNumber,
  @NotBlank String email) {
}
