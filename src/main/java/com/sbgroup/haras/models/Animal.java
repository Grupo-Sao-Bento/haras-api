package com.sbgroup.haras.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbgroup.haras.enums.AnimalBreed;
import com.sbgroup.haras.enums.AnimalCoat;
import com.sbgroup.haras.enums.AnimalGender;
import com.sbgroup.haras.enums.AnimalType;

import lombok.*;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "Animals")
public class Animal implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false)
  private String name;
  
  @Column(nullable = false)
  private AnimalType type;
  
  @Column(nullable = false)
  private AnimalGender gender;
  
  @Column(nullable = false)
  private AnimalCoat coat;

  @ManyToOne
  @JoinColumn(nullable = false)
  private User createdBy;

  @Column(nullable = false)
  private Timestamp createdAt;

  @ManyToOne
  @JoinColumn
  private User updatedBy;

  @ManyToOne
  private Animal mother;

  @ManyToOne
  private Animal father;

  @ManyToOne
  private Client owner;

  @JsonProperty(value="isAlive")
  private boolean isAlive;

  private Timestamp updatedAt;
  private Timestamp birthDate;
  private String registry;
  private double dailyFee;
  private AnimalBreed breed;
  // TODO: Stays
  // TODO: Procedures
  // TODO: Farm

  public Animal(String name, AnimalType type, AnimalGender gender, AnimalCoat coat, User createdBy, Timestamp createdAt) {
    this.name = name;
    this.type = type;
    this.gender = gender;
    this.coat = coat;
    this.createdBy = createdBy;
    this.createdAt = createdAt;
  }
  
}
