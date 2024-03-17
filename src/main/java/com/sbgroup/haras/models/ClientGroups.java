package com.sbgroup.haras.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clientGroups")
public class ClientGroups implements Serializable {

  private int clientQuota;
}
