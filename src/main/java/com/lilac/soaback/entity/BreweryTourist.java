package com.lilac.soaback.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class BreweryTourist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String touristName;
    @ManyToOne
    private Brewery brewery;
}
