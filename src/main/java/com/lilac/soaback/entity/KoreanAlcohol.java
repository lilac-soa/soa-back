package com.lilac.soaback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class KoreanAlcohol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String alcoholName;
    private float abv;
    private String specification;
    private String mainIngredient;
    private String manufacturer;


}
