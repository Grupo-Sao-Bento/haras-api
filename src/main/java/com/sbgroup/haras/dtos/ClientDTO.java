package com.sbgroup.haras.dtos;

import jakarta.validation.constraints.NotBlank;

public record ClientDTO(
  @NotBlank String firstName,
  @NotBlank String lastName,
  @NotBlank String phoneNumber,
  @NotBlank String email,
  @NotBlank String cpf,
  @NotBlank String cnpj,
  String address,
  String city,
  String state,
  String country,
  String cep) {
}
