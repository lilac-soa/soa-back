package com.lilac.soaback.tourist.controller;

import com.lilac.soaback.entity.BreweryTourist;
import com.lilac.soaback.global.util.ApiResponse;
import com.lilac.soaback.tourist.dto.response.TouristResponse;
import com.lilac.soaback.tourist.service.TouristService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class TouristController {
    private final TouristService touristService;

    /**
     * 여행지 id로 조회
     *
     * @param tourId 조회할 여행지의 id
     * @param breweryId 조회할 양조장의 id
     * @return TouristResponse 값
     */
    @GetMapping("/breweries/{breweryId}/tours/{tourId}")
    public ApiResponse<TouristResponse> tourist(
            @PathVariable String breweryId,
            @PathVariable String tourId) {
        return ApiResponse.success(touristService.getTourist(tourId));
    }

    /**
     * 여행지 전체 조회
     *
     * @param breweryId 조회할 양조장의 id
     * @return TouristResponse 값
     */
    @GetMapping("/breweries/{breweryId}/tours")
    public ApiResponse<List<BreweryTourist>> tourist(@PathVariable String breweryId) {
        return ApiResponse.success(touristService.getTourists(breweryId));
    }

}
