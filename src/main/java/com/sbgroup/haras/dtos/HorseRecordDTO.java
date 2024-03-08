package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotBlank;

public record HorseRecordDTO(
        @NotBlank String name,
        @NotBlank String gender,
        @NotBlank String coat ) {
}
