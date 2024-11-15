package com.lilac.soaback.alcohol.dto.response;

import lombok.Builder;

@Builder
public record KoreanAlcoholResponse(
        long id,
        String alcoholName,
        String abv,
        String specification,
        String mainIngredient,
        String manufacturer
        ) {
}
