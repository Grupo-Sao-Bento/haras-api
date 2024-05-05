package com.sbgroup.haras.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Procedures")
public class Procedures {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String description;
    private String category;
    private LocalDate data;
    private Timestamp updateAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User ResponsibleName;

    @ManyToOne
    @JoinColumn
    private User updateBy;

    @Column(nullable = false)
    private Timestamp createdAt;
}
