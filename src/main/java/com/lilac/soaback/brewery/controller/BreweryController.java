package com.lilac.soaback.brewery.controller;

import com.lilac.soaback.brewery.dto.response.BreweryResponse;
import com.lilac.soaback.brewery.service.BreweryService;
import com.lilac.soaback.entity.Brewery;
import com.lilac.soaback.global.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BreweryController {
    private final BreweryService breweryService;

    /**
     * 양조장 id로 조회
     *
     * @param id 조회할 양조장의 id
     * @return BreweryResponse 값
     */
    @GetMapping("/breweries/{id}")
    public ApiResponse<BreweryResponse> brewery(@PathVariable String id) {
        return ApiResponse.success(breweryService.getBrewery(id));
    }

    /**
     * 양조장 전체 조회
     *
     * @return BreweryResponse 값
     */
    @GetMapping("/breweries")
    public ApiResponse<List<Brewery>> brewery() {
        return ApiResponse.success(breweryService.getBreweries());
    }
}
