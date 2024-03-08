package com.sbgroup.haras.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "Owners")
public class OwnerModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ownerId;
    private boolean isGroup;
    // clientId
    // groupId
}
