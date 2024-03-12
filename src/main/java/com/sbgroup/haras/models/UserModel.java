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
@Table(name = "Users")
public class UserModel implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID userId;
  
  private String firstName;
  
  private String lastName;
  
  // farmId
}
