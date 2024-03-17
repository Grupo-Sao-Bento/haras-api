package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotBlank;
public record ProcedureTypeRecordDTO(
        @NotBlank String name) {
}
