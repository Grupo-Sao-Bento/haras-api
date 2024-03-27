package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(
  @NotBlank String login,
  @NotBlank String password ) {
}
