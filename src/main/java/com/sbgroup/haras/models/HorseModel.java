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
@Table(name = "Horses")
public class HorseModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "horses_id")
  private UUID id;
  @NotBlank
  @Column(name = "horses_name")
  private String name;
  @OneToMany
  @JoinColumn(name = "animal_type_id")
  private int animalType;
  @NotBlank
  @Column(name = "horses_gender")
  private String gender;
  @OneToMany
  @JoinColumn(name = "coats_id")
  private int coatId;
  @ManyToMany
  @JoinColumn(name = "clients_created_by")
  private String createdBy;
  @NotBlank
  @Column(name = "horses_created_at")
  private Timestamp createdAt;
  @ManyToMany
  @JoinColumn(name = "clients_update_by")
  private String updateBy;
  @NotBlank
  @Column(name = "horses_update_at")
  private Timestamp updateAt;
  @ManyToMany
  @JoinColumn(name = "users_farm_id")
  private String farmId;
}
