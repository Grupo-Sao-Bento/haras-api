package com.sbgroup.haras.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "FarmGroups")
public class GroupModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "groups_id")
  private UUID id;
  @NotBlank
  @Column(name = "groups_name")
  private String name;
  @ManyToMany
  @JoinColumn(name = "users_farm_id")
  private String farmId;
}
