package com.lilac.soaback.alcohol.service;

import com.lilac.soaback.alcohol.dto.AlcoholResponse;
import com.lilac.soaback.entity.KoreanAlcohol;

import java.util.List;

public interface AlcoholService {
    KoreanAlcohol getAlcohol(String id);
    List<KoreanAlcohol> getAlcohols();
}
