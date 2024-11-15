package com.lilac.soaback.alcohol.controller;

import com.lilac.soaback.alcohol.dto.response.KoreanAlcoholResponse;
import com.lilac.soaback.alcohol.service.KoreanAlcoholService;
import com.lilac.soaback.global.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class KoreanAlcoholController {
    private final KoreanAlcoholService koreanAlcoholService;

    /**
     * 전통주 아이디로 조회
     *
     * @param id 조회할 전통주 아이디
     * @return KoreanResponse 값
     */
    @GetMapping("/koreanAlcohol/{id}")
    public ApiResponse<KoreanAlcoholResponse> koreanAlcohol(
            @PathVariable("id") long id
    ) {
        return ApiResponse.success(koreanAlcoholService.getKoreanAlcohol(id));
    }
}
