package com.sbgroup.haras.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnimalBreed {
  APPALOOZA("appalooza"),
  ANDALUZ("andaluz"),
  BRETAO("bretão"),
  CAMPOLINA("campolina"),
  ARABE("árabe"),
  CRIOULO("crioulo"),
  LUSITANO("lusitano"),
  MANGALARGA("mangalarga"),
  MACHADOR("machador"),
  NORDESTINO("nordestino"),
  QUARTO_DE_MILHA("quarto_de_milha");

  private final String breed;
}
