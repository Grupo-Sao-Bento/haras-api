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
@Table(name = "Stay")
public class Stay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDate start;
    private LocalDate end;
    private Timestamp updateAt;

    @Column(nullable = false)
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User ResponsibleName;

    @ManyToOne
    @JoinColumn
    private User updateBy;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Animal animal;

}
