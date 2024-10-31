package com.lilac.soaback.tourist.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record TouristResponse(
        long id,
        String touristName,
        String address,
        String content,
        String type,
        List<String> imageList
){
}