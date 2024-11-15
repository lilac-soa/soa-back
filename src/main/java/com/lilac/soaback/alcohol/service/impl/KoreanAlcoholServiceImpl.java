package com.lilac.soaback.alcohol.service.impl;

import com.lilac.soaback.alcohol.dto.response.KoreanAlcoholResponse;
import com.lilac.soaback.alcohol.repository.KoreanAlcoholRepository;
import com.lilac.soaback.alcohol.service.KoreanAlcoholService;
import com.lilac.soaback.entity.KoreanAlcohol;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class KoreanAlcoholServiceImpl implements KoreanAlcoholService {
    private final KoreanAlcoholRepository koreanAlcoholRepository;

    @Override
    public KoreanAlcoholResponse getKoreanAlcohol(long id) {
        KoreanAlcohol koreanAlcohol = koreanAlcoholRepository.getReferenceById(id);

        return KoreanAlcoholResponse.builder()
                .id(koreanAlcohol.getId())
                .alcoholName(koreanAlcohol.getAlcoholName())
                .abv(koreanAlcohol.getAbv())
                .specification(koreanAlcohol.getSpecification())
                .manufacturer(koreanAlcohol.getManufacturer())
                .build();
    }
}
