package com.lilac.soaback.alcohol.dto;

import lombok.Builder;

@Builder
public record AlcoholResponse(
        String breweryName,
        String owner,
        String location,
        String philosophy,
        double lat,
        double lon,
        String webPage,
        String phone,
        String openingHours,
        String closedDays) {
}
