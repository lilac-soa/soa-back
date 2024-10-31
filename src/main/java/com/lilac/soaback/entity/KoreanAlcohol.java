package com.lilac.soaback.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    private Brewery brewery;

    @ManyToOne
    private Type type;

    @OneToMany(mappedBy = "koreanAlcohol", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KoreanAlcoholImage> koreanAlcoholImageList = new ArrayList<>();

}
