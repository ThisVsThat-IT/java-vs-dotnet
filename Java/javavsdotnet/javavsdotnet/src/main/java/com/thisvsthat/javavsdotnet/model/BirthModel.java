package com.thisvsthat.javavsdotnet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class BirthModel {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String lastname;

    @Column
    private LocalDate birthDate;
}
