package com.lilac.soaback.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
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

}
