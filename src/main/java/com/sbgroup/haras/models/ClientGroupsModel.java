package com.sbgroup.haras.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ClientGroups")
public class ClientGroupsModel implements Serializable {

  @OneToMany
  @JoinColumn(name = "clients_id")
  private UUID id;
  @ManyToMany
  @JoinColumn(name = "groups_id")
  private UUID groupId;
  @NotNull
  @Column(name = "client_quota")
  private int clientQuota;
}
