package com.lilac.soaback.tourist.controller;

import com.lilac.soaback.docs.BaseDocumentTest;
import com.lilac.soaback.tourist.dto.response.TouristResponse;
import com.lilac.soaback.tourist.service.TouristService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.ArrayList;
import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = {
                TouristController.class
        }
)
class TouristControllerTest extends BaseDocumentTest {

    @MockBean
    private TouristService touristService;

    @Test
    @DisplayName("여행지 조회 테스트")
    void findByIdTest() throws Exception {
        List<String> imageList = new ArrayList<>();
        imageList.add("image1.jpg");
        imageList.add("image2.jpg");

        TouristResponse response = TouristResponse.builder()
                .id(1L)
                .touristName("관광지")
                .address("한국")
                .content("내용")
                .type("여행")
                .imageList(imageList)
                .build();
        given(touristService.getTourist(anyLong())).willReturn(response);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/tour/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(document(snippetPath,
                        "여행지 id 조회 테스트",
                        pathParameters(
                                parameterWithName("id").description("여행지 아이디")
                        ),
                        responseFields(
                                fieldWithPath("header.resultCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                                fieldWithPath("header.successful").type(JsonFieldType.BOOLEAN).description("성공 여부"),
                                fieldWithPath("body.data.id").type(JsonFieldType.NUMBER).description("여행지 아이디"),
                                fieldWithPath("body.data.touristName").type(JsonFieldType.STRING).description("여행지 이름"),
                                fieldWithPath("body.data.address").type(JsonFieldType.STRING).description("여행지 주소"),
                                fieldWithPath("body.data.content").type(JsonFieldType.STRING).description("여행지 내용"),
                                fieldWithPath("body.data.type").type(JsonFieldType.STRING).description("여행지 종류"),
                                fieldWithPath("body.data.imageList").type(JsonFieldType.ARRAY).description("이미지 리스트")
                        )
                ));
    }
}