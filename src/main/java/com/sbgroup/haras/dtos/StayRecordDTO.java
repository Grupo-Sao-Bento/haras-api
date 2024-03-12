package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record StayRecordDTO(
  @NotNull Timestamp ingress,
  @NotNull Timestamp egress) {
}
