package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;

public record ProcedureDTO(
  @NotNull int procedureTypeId,
  @NotBlank String farmId,
  @NotNull Timestamp date,
  @NotBlank String responsibleId,
  @NotNull Timestamp updateAt,
  @NotBlank String updateBy) {
}
