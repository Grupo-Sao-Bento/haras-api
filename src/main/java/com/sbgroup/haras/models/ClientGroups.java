package com.sbgroup.haras.models;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "clientGroups")
public class ClientGroups implements Serializable {

    private int clientQuota;
}
