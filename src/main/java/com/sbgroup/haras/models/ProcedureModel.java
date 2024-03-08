package com.sbgroup.haras.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "Procedures")
public class ProcedureModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID procedureId;
    // procedureType
    private Timestamp date;
    // userId
}
