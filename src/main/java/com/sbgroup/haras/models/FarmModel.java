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
@Table(name = "Farms")
public class FarmModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "farms_id")
  private UUID id;
  @NotBlank
  @Column(name = "farms_name")
  private String name;
  @NotBlank
  @Column(name = "farms_address")
  private String address;
  @NotBlank
  @Column(name = "farms_city")
  private String city;
  @NotBlank
  @Column(name = "farms_state")
  private String state;
  @NotBlank
  @Column(name = "farms_country")
  private String country;
  @Column(name = "farms_cep")
  private String cep;
  @NotBlank
  @Column(name = "farms_created_at")
  private Timestamp createdAt;
}
