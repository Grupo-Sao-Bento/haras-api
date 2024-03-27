package com.sbgroup.haras.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnimalType {
    POTRO("potro"),
    EGUA("egua"),
    GARANHAO("garanhao");
    
    private final String type;
}
