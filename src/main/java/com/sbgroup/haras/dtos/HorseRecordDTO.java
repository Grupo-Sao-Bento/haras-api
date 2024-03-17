package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record HorseRecordDTO(
  @NotBlank String name,
  @NotNull int animalType,
  @NotBlank String gender,
  @NotNull int coatId,
  @NotBlank String createdBy,
  @NotNull Timestamp createdAt,
  @NotBlank String updateBy,
  @NotNull Timestamp updateAt,
  @NotBlank String farmId) {
}
