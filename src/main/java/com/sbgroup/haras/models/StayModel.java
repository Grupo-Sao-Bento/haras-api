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
@Table(name = "Stays")
public class StayModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "stay_id")
  private UUID id;
  @ManyToMany
  @JoinColumn(name = "users_farm_id")
  private String farmId;
  @ManyToMany
  @JoinColumn(name = "horses_id")
  private String animalId;
  @NotBlank
  @Column(name = "stay_entry")
  private Timestamp entry;
  @Column(name = "stay_forebeen_egress")
  private Timestamp forebeenEgress;
  @Column(name = "stay_egress")
  private Timestamp egress;
}
