package com.sbgroup.haras.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "Clients")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn
    private User updateBy;

    private String cpf;
    private String cnpj;
    private String address;
    private String city;
    private String state;
    private String country;
    private String cep;
    private Timestamp updateAt;

    @JsonProperty(value="isOrganization")
    private boolean isOrganization;

    private String organizationName;

    // TODO: Farm

    public Client(String firstName, String lastName, String phoneNumber, String email, Timestamp createdAt, User createdBy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

}
