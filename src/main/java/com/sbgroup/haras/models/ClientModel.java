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
@Table(name = "Clients")
public class ClientModel implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID clientId;
  
  private String name;
  
  // farmId
}
