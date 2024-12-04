package com.example.TugasBesar.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Setlist {
    @Id
    private Long id;
    private String songTitle;

    // Getter and Setter
}
