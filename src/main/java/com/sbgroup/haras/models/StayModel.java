package com.sbgroup.haras.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "Stays")
public class StayModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID stayId;
  private String farmId;
  private String animalId;
  private Timestamp entry;
  private Timestamp forebeenExit;
  private Timestamp exit;
}
