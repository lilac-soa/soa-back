package com.lilac.soaback.entity;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Brewery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String breweryName;
    private String owner;
    private String location;
    private String philosophy;
    private float lat;
    private float lon;
    private String webPage;
    private String phone;
    private String openingHours;
    private String closedDays;

    @OneToMany(mappedBy = "brewery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KoreanAlcohol> koreanAlcoholList = new ArrayList<>();


    @OneToMany(mappedBy = "brewery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BreweryTourist> breweryTouristList = new ArrayList<>();

    @OneToMany(mappedBy = "brewery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BreweryImage> breweryImageList = new ArrayList<>();

    @ManyToOne
    private Region region;

}
