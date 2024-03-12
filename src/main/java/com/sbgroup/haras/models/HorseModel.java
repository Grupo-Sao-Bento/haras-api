package com.sbgroup.haras.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Horses")
public class HorseModel implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID horseId;
  
  private String name;
  
  private String gender;
  
  private String coat;
  
  // registry
  
  // farmId
  
  // ownerId
}
