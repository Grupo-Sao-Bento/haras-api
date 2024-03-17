package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotBlank;

public record ProcedureTypeDTO(
  @NotBlank String name) {
}
