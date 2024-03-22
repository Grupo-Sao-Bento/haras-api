package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;

public record FarmDTO(
  @NotBlank String name,
  @NotBlank String address,
  @NotBlank String city,
  @NotBlank String state,
  @NotBlank String country,
  @NotNull Timestamp createdAt) {
}
