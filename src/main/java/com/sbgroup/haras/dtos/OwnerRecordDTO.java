package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotNull;

public record OwnerRecordDTO(
        @NotNull boolean isGroup ) {
}
