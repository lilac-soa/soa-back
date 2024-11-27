package com.lilac.soaback.brewery.service.impl;

import com.lilac.soaback.brewery.dto.response.BreweryResponse;
import com.lilac.soaback.brewery.exception.NotFoundBreweryException;
import com.lilac.soaback.brewery.repository.BreweryRepository;
import com.lilac.soaback.brewery.service.BreweryService;
import com.lilac.soaback.entity.Brewery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BreweryServiceImpl implements BreweryService {
    private final BreweryRepository breweryRepository;

    @Override
    public BreweryResponse getBrewery(String id) {
        UUID uuid = UUID.fromString(id);

        Brewery brewery = breweryRepository.findById(uuid).orElseThrow(()-> new NotFoundBreweryException(""));

        return BreweryResponse.builder()
                .id(brewery.getId())
                .breweryName(brewery.getBreweryName())
                .owner(brewery.getOwner())
                .location(brewery.getLocation())
                .philosophy(brewery.getPhilosophy())
                .lat(brewery.getLat())
                .lon(brewery.getLon())
                .webPage(brewery.getWebPage())
                .phone(brewery.getPhone())
                .openingHours(brewery.getOpeningHours())
                .closedDays(brewery.getClosedDays())
                .build();
    }

    @Override
    public List<Brewery> getBreweries() {
        return breweryRepository.findAll();
    }
}
