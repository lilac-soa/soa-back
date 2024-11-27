package com.lilac.soaback.brewery.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record BreweryResponse(
        UUID id,
        String breweryName,
        String owner,
        String location,
        String philosophy,
        double lat,
        double lon,
        String webPage,
        String phone,
        String openingHours,
        String closedDays
) {}