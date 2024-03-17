package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthRecordDTO(
  @NotBlank String login,
  @NotBlank String password) {
}
