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
public class ProcedureTypeModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "procedures_type_id")
  private UUID id;
  @Column(name = "procedures_type_name")
  private String name;
}
