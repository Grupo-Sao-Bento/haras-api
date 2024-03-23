package com.sbgroup.haras.dtos;

import com.sbgroup.haras.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO(
  @NotBlank @Size(max = 55) String login,
  @NotBlank @Size(max = 35) String firstName,
  @NotBlank @Size(max = 125) String lastName,
  @NotNull UserRole role,
  @NotBlank String password) {
}
