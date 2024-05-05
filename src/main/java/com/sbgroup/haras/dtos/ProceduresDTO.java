package com.sbgroup.haras.dtos;

import java.time.LocalDate;

public record ProceduresDTO(String description, String category, LocalDate data) {
}
