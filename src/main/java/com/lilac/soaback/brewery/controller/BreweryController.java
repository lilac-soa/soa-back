package com.lilac.soaback.brewery.controller;

import com.lilac.soaback.brewery.dto.response.BreweryResponse;
import com.lilac.soaback.brewery.service.BreweryService;
import com.lilac.soaback.global.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/brewery/{id}")
    public ApiResponse<BreweryResponse> brewery(@PathVariable long id) {
        return ApiResponse.success(breweryService.getBrewery(id));
    }
}
