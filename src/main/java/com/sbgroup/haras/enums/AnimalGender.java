package com.sbgroup.haras.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnimalGender {
  MALE("male"),
  FEMALE("female");
  
  private final String gender;
}
