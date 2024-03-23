package com.sbgroup.haras.dtos;

import com.sbgroup.haras.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
  @NotBlank String login,
  @NotBlank String firstName,
  @NotBlank String lastName,
  @NotNull UserRole role,
  @NotBlank String password) {
}
