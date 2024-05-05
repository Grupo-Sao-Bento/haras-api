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
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "Farms")
public class Farm implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String address;

  @Column(nullable = false)
  private String city;
  
  @Column(nullable = false)
  private String state;
  
  @Column(nullable = false)
  private String country;
  
  @Column(nullable = false)
  private Timestamp createdAt;
  
  private String cep;
  
}
