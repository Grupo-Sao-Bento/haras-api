package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record StayDTO(LocalDate start, LocalDate end, @NotNull UUID animal) {
}

