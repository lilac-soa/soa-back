package com.lilac.soaback.brewery.service;

import com.lilac.soaback.brewery.dto.response.BreweryResponse;
import com.lilac.soaback.entity.Brewery;

import java.util.List;

public interface BreweryService {
    BreweryResponse getBrewery(String id);
    List<Brewery> getBreweries();
}
