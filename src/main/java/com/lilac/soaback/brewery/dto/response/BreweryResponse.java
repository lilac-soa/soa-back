package com.lilac.soaback.brewery.dto.response;

import lombok.Builder;

@Builder
public record BreweryResponse(
        Long id,
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