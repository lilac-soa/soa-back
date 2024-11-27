package com.lilac.soaback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BreweryTourist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String touristName;
    private String address;
    private String content;
    private String type;

    @ManyToOne
    private Brewery brewery;

    @OneToMany(mappedBy = "breweryTourist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BreweryTouristImage> breweryTouristImageList = new ArrayList<>();

    public BreweryTourist(String name, String type){
        this.touristName = name;
        this.type = type;
    }
}
