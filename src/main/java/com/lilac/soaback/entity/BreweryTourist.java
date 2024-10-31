package com.lilac.soaback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BreweryTourist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String touristName;
    private String address;
    private String content;
    private String type;

    @ManyToOne
    private Brewery brewery;

    @OneToMany(mappedBy = "breweryTourist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BreweryTouristImage> breweryTouristImageList = new ArrayList<>();
}
