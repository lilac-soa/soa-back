package com.lilac.soaback.alcohol.service.impl;

import com.lilac.soaback.alcohol.dto.AlcoholResponse;
import com.lilac.soaback.alcohol.exception.AlcoholNotFoundException;
import com.lilac.soaback.alcohol.repository.KoreanAlcoholRepository;
import com.lilac.soaback.alcohol.service.AlcoholService;
import com.lilac.soaback.brewery.exception.NotFoundBreweryException;
import com.lilac.soaback.entity.KoreanAlcohol;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class AlcoholServiceImpl implements AlcoholService {

    private final KoreanAlcoholRepository koreanAlcoholRepository;

    @Override
    public List<KoreanAlcohol> getAlcohols() {

        return koreanAlcoholRepository.findAll();
    }

    @Override
    public KoreanAlcohol getAlcohol(String id) {
        UUID uuid = UUID.fromString(id);

        return koreanAlcoholRepository.findById(uuid).orElseThrow(
                ()-> new AlcoholNotFoundException("alcohol not found : " + uuid)
        );
    }
}
