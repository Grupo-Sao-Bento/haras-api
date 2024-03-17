package com.sbgroup.haras.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "ProcedureTypes")
public class ProcedureType {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID procedureTypeId;
  private String name;

}
