package com.sbgroup.haras.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ProcedureTypes")
public class ProcedureType {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID procedureTypeId;
  private String name;

}
