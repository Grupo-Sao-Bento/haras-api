package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;

public record UserRecordDTO(
        @NotBlank String email,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotNull int roleId,
        @NotBlank String farmId,
        @NotNull Timestamp createdAt) {
}
