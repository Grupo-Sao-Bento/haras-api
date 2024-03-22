package com.sbgroup.haras.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "Procedures")
public class ProcedureModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "procedure_id")
  private UUID id;
  @ManyToOne
  @JoinColumn(name = "procedures_type_id")
  private int procedureTypeId;
  @ManyToMany
  @JoinColumn(name = "users_farm_id")
  private String farmId;
  @NotBlank
  @Column(name = "procedures_date")
  private Timestamp date;
  @Column(name = "procedures_responsible_id")
  private String responsibleId;
  @NotBlank
  @Column(name = "procedures_update_at")
  private Timestamp updateAt;
  @ManyToMany
  @JoinColumn(name = "clients_update_by")
  private String updateBy;
}
