package com.lilac.soaback.tourist.dto.response;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record TouristResponse(
        UUID id,
        String touristName,
        String address,
        String content,
        String type,
        List<String> imageList
){
}