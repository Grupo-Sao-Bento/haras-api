package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record ProcedureRecordDTO(
  @NotNull Timestamp date) {
}
