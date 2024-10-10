package com.lilac.soaback.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class BreweryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productName;
    @ManyToOne
    private Brewery brewery;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "breweryProduct", orphanRemoval = true)
    private List<BreweryProductImage> breweryProductImages;
}
