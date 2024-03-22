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
@Table(name = "Clients")
public class ClientModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "clients_id")
  private UUID id;
  @NotBlank
  @Column(name = "clients_first_name")
  private String firstName;
  @NotBlank
  @Column(name = "clients_last_name")
  private String lastName;
  @NotBlank
  @Column(name = "clients_phone_number")
  private String phoneNumber;
  @NotBlank
  @Column(name = "clients_email")
  private String email;
  @Column(name = "clients_address")
  private String address;
  @Column(name = "clients_city")
  private String city;
  @Column(name = "clients_state")
  private String state;
  @Column(name = "clients_country")
  private String country;
  @Column(name = "clients_cep")
  private String cep;
  @NotBlank
  @Column(name = "clients_created_at")
  private Timestamp createdAt;
  @ManyToMany
  @JoinColumn(name = "clients_created_by")
  private String createdBy;
  @NotBlank
  @Column(name = "clients_update_at")
  private Timestamp updateAt;
  @ManyToMany
  @JoinColumn(name = "horses_update_by")
  private String updateBy;
  @ManyToMany
  @JoinColumn(name = "users_farm_id")
  private String farmId;
}
