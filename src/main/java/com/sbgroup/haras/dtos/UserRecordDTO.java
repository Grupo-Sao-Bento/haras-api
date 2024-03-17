package com.sbgroup.haras.dtos;

import com.sbgroup.haras.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record UserRecordDTO(
  @NotBlank String email,
  @NotBlank String firstName,
  @NotBlank String lastName,
  @NotNull UserRole role,
  @NotBlank String farmId,
  @NotNull Timestamp createdAt,
  @NotBlank String password) {
}
