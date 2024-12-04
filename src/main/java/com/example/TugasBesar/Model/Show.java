package com.example.TugasBesar.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Show {
    @Id
    private Long id;
    private String name;
    private String venue;
    private String date;

    // Getter and Setter
}
