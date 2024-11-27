package com.lilac.soaback.alcohol.controller;

import com.lilac.soaback.alcohol.service.AlcoholService;
import com.lilac.soaback.brewery.dto.response.BreweryResponse;
import com.lilac.soaback.brewery.service.BreweryService;
import com.lilac.soaback.entity.Brewery;
import com.lilac.soaback.entity.KoreanAlcohol;
import com.lilac.soaback.global.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AlcoholController {
    private final AlcoholService alcoholService;

    /**
     * 술 id로 조회
     *
     * @param id 조회할 술 id
     * @return 값
     */
    @GetMapping("/alcohols/{id}")
    public ApiResponse<KoreanAlcohol> brewery(@PathVariable String id) {
        return ApiResponse.success(alcoholService.getAlcohol(id));
    }

    /**
     * 술 전체 조회
     *
     * @return  값
     */
    @GetMapping("/alcohols")
    public ApiResponse<List<KoreanAlcohol>> brewery() {
        return ApiResponse.success(alcoholService.getAlcohols());
    }
}
