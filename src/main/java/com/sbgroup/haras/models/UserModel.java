package com.sbgroup.haras.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class UserModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID userId;
  private String email;
  private String firstName;
  private String lastName;
  private int roleId;
  private String farmId;
  private Timestamp createdAt;
}
