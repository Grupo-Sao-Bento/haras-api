package com.sbgroup.haras.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnimalCoat {
    BRANCA("branca"),
    PRETA("preta"),
    ALAZA("alazã"),
    CASTANHA("castanha"),
    BAIA("baia"),
    PELO_DE_RATO("pelo de rato"),
    TORDILHA("tordilha"),
    ROSILHA("rosilha"),
    LOBUNA("lobuna"),
    RUAO("ruão"),
    PAMPA("pampa"),
    LEOPARDO("leopardo"),
    MANTADO("mantado"),
    NEVADO("nevado");
    
    private final String coat;
}
