package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotNull;

public record FarmRecordDTO(
        @NotNull String name ) {
}
