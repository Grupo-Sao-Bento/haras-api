package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotNull;

public record ClientGroupsDTO(
  @NotNull int clientQuota) {
}
