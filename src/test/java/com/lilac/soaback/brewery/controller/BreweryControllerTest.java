package com.lilac.soaback.brewery.controller;

import com.lilac.soaback.brewery.dto.response.BreweryResponse;
import com.lilac.soaback.brewery.service.BreweryService;
import com.lilac.soaback.docs.BaseDocumentTest;
import com.lilac.soaback.tourist.dto.response.TouristResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.ArrayList;
import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = {
                BreweryController.class
        }
)
class BreweryControllerTest extends BaseDocumentTest {
    @MockBean
    private BreweryService breweryService;

    @Test
    @DisplayName("양조장 조회 테스트")
    void findByIdTest() throws Exception {
        BreweryResponse response = new BreweryResponse(
                1L,
                "Mountain Peak Brewery",       // breweryName
                "John Doe",                    // owner
                "1234 Craft Rd, Beer City",    // location
                "Brewing with passion and precision", // philosophy
                37.7749,                       // lat
                -122.4194,                     // lon
                "http://mountainpeakbrewery.com", // webPage
                "+1-800-555-0123",             // phone
                "10:00 AM - 10:00 PM",         // openingHours
                "Sundays"                      // closedDays
        );

        given(breweryService.getBrewery(anyLong())).willReturn(response);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/brewery/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(document("brewery-findById",
                        pathParameters(
                                parameterWithName("id").description("양조장 아이디")
                        ),
                        responseFields(
                                fieldWithPath("header.resultCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                                fieldWithPath("header.successful").type(JsonFieldType.BOOLEAN).description("성공 여부"),
                                fieldWithPath("body.data.id").type(JsonFieldType.NUMBER).description("양조장 아이디"),
                                fieldWithPath("body.data.breweryName").type(JsonFieldType.STRING).description("양조장 이름"),
                                fieldWithPath("body.data.owner").type(JsonFieldType.STRING).description("양조장 소유자"),
                                fieldWithPath("body.data.location").type(JsonFieldType.STRING).description("양조장 위치"),
                                fieldWithPath("body.data.philosophy").type(JsonFieldType.STRING).description("양조 철학"),
                                fieldWithPath("body.data.lat").type(JsonFieldType.NUMBER).description("위도"),
                                fieldWithPath("body.data.lon").type(JsonFieldType.NUMBER).description("경도"),
                                fieldWithPath("body.data.webPage").type(JsonFieldType.STRING).description("웹 페이지"),
                                fieldWithPath("body.data.phone").type(JsonFieldType.STRING).description("전화번호"),
                                fieldWithPath("body.data.openingHours").type(JsonFieldType.STRING).description("영업 시간"),
                                fieldWithPath("body.data.closedDays").type(JsonFieldType.STRING).description("휴무일")
                        )
                ));
    }
}