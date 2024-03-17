package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotBlank;

public record GroupRecordDTO(
        @NotBlank String name,
        @NotBlank String farmId) {
}
