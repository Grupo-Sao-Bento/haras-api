package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserRecordDTO(
        @NotBlank String firstName,
        @NotBlank String lastName ) {
}
