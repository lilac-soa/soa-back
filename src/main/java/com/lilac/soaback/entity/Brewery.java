package com.lilac.soaback.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Brewery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String breweryName;
    private String owner;
    private String location;
    private String philosophy;
    private double lat;
    private double lon;
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

    public Brewery(String breweryName, String location, String openingHours,
                   String closedDays, String phone, String philosophy,
                   String webPage, double lat, double lon) {
        this.breweryName = breweryName;
        this.location = location;
        this.openingHours = openingHours;
        this.closedDays = closedDays;
        this.phone = phone;
        this.philosophy = philosophy;
        this.webPage = webPage;
        this.lat = lat;
        this.lon = lon;
    }

    public void addAlcohol(KoreanAlcohol alcohol) {
        koreanAlcoholList.add(alcohol);
        alcohol.setBrewery(this);
    }

    public void addBreweryTourist(BreweryTourist breweryTourist) {
        breweryTouristList.add(breweryTourist);
        breweryTourist.setBrewery(this);
    }
}
