package com.sbgroup.haras.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "animalTypes")
public class AnimalTypesModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID animalTypeId;
    private String name;
}
