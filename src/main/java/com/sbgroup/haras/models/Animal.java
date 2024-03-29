package com.sbgroup.haras.models;

import com.sbgroup.haras.enums.AnimalCoat;
import com.sbgroup.haras.enums.AnimalGender;
import com.sbgroup.haras.enums.AnimalType;
import jakarta.persistence.*;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

  private Timestamp updatedAt;

  // Farm

  public Animal(String name, AnimalType type, AnimalGender gender, AnimalCoat coat, User createdBy, Timestamp createdAt) {
    this.name = name;
    this.type = type;
    this.gender = gender;
    this.coat = coat;
    this.createdBy = createdBy;
    this.createdAt = createdAt;
  }
  
}
