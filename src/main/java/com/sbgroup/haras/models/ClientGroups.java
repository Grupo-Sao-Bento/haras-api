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
@Table(name = "ClientGroups")
public class ClientGroups implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID clientGroupsId;

  private int clientQuota;
}
