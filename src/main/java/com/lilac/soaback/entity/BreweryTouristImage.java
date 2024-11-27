package com.lilac.soaback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BreweryTouristImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private BreweryTourist breweryTourist;

    @ManyToOne
    private Image image;
}
