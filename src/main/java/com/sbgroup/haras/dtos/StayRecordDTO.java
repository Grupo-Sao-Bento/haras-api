package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record StayRecordDTO(
  @NotBlank String farmId,
  @NotBlank String animalId,
  @NotNull Timestamp entry,
  @NotNull Timestamp forebeenEgress,
  @NotNull Timestamp egress) {
}
