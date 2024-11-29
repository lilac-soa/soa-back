package com.lilac.soaback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class KoreanAlcohol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String alcoholName;
    private String abv;
    private String specification;
    private String mainIngredient;
    private String manufacturer;
    private LocalDateTime rgsde;

    @ManyToOne
    private Brewery brewery;

    @ManyToOne
    private Type type;

    @OneToMany(mappedBy = "koreanAlcohol", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KoreanAlcoholImage> koreanAlcoholImageList = new ArrayList<>();

    public KoreanAlcohol(String alcoholName, String abv){
        this.alcoholName = alcoholName;
        this.abv = abv;
    }

    public KoreanAlcohol(String alcoholName, String abv, String specification, String mainIngredient, String manufacturer) {
        this.alcoholName = alcoholName;
        this.abv = abv;
        this.specification = specification;
        this.mainIngredient = mainIngredient;
        this.manufacturer = manufacturer;
        this.rgsde = LocalDateTime.now();
    }
}
