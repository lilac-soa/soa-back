package com.lilac.soaback.tourist.service;

import com.lilac.soaback.entity.BreweryTourist;
import com.lilac.soaback.tourist.dto.response.TouristResponse;

import java.util.List;

public interface TouristService {

    TouristResponse getTourist(String id);
    List<BreweryTourist> getTourists(String breweryId);
}
