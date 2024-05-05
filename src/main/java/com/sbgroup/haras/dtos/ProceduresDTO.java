package com.sbgroup.haras.dtos;

import java.sql.Timestamp;

public record ProceduresDTO(String description, String category, Timestamp data) {
}
