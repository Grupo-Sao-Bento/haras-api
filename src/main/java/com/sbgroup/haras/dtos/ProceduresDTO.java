package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record ProceduresDTO(String description, String category, LocalDate data, @NotNull UUID animalId) {
}
