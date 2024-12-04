package com.example.TugasBesar.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Artist {
    @Id
    private Long id;
    private String name;

    // Getter and Setter
}
