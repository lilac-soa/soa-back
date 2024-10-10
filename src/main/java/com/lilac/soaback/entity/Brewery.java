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
    private String foundedYear;
    private String selectionYear;
    private String philosophy;
    private float lat;
    private float lon;

    @OneToMany(mappedBy = "brewery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BreweryType> breweryTypeList = new ArrayList<>();

    @OneToMany(mappedBy = "brewery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BreweryTourist> breweryTouristList = new ArrayList<>();

    @OneToMany(mappedBy = "brewery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BreweryProduct> breweryProductList = new ArrayList<>();

    @OneToMany(mappedBy = "brewery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BreweryImage> breweryImageList = new ArrayList<>();

    @OneToMany(mappedBy = "brewery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecommendedRestaurant> recommendedRestaurantList = new ArrayList<>();

    @OneToOne
    private Region region;

}
