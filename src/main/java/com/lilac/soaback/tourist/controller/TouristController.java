package com.lilac.soaback.tourist.controller;

import com.lilac.soaback.global.util.ApiResponse;
import com.lilac.soaback.tourist.dto.response.TouristResponse;
import com.lilac.soaback.tourist.service.TouristService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class TouristController {
    private final TouristService touristService;

    /**
     * 여행지 id로 조회
     *
     * @param id 조회할 여행지의 id
     * @return TouristResponse 값
     */
    @GetMapping("/tour/{id}")
    public ApiResponse<TouristResponse> tourist(@PathVariable long id) {
        return ApiResponse.success(touristService.getTourist(id));
    }


}
