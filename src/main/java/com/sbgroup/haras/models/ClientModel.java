package com.sbgroup.haras.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Clients")
public class ClientModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String firstName;
  private String phoneNumber;
  private String email;
  private String address;
  private String city;
  private String state;
  private String country;
  private String cep;
  private Timestamp createdAt;
  private String createdBy;
  private Timestamp updateAt;
  private String updateBy;
  private String farmId;
}
