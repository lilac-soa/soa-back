package com.lilac.soaback.koreanAlcohol.controller;

import com.lilac.soaback.alcohol.controller.KoreanAlcoholController;
import com.lilac.soaback.alcohol.dto.response.KoreanAlcoholResponse;
import com.lilac.soaback.alcohol.service.KoreanAlcoholService;
import com.lilac.soaback.docs.BaseDocumentTest;
import com.lilac.soaback.entity.KoreanAlcohol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;


@WebMvcTest(
        controllers = {
                KoreanAlcoholController.class
        }
)
public class KoreanAlcoholControllerTest extends BaseDocumentTest {
    @MockBean
    private KoreanAlcoholService koreanAlcoholService;

    @Test
    @DisplayName("전통주 조회 테스트")
    void findByIdTest() throws Exception {
        KoreanAlcoholResponse response = new KoreanAlcoholResponse(
                1L,
                "전통주1",
                "12.0%",
                "가로:-, 세로:-",
                "",
                "양조장1"
        );

        given(koreanAlcoholService.getKoreanAlcohol(anyLong())).willReturn(response);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/koreanAlcohol/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("koreanAlcohol-findById",
                        pathParameters(
                                parameterWithName("id").description("전통주 아이디")
                        ),
                        responseFields(
                                fieldWithPath("header.resultCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                                fieldWithPath("header.successful").type(JsonFieldType.BOOLEAN).description("성공 여부"),
                                fieldWithPath("body.data.id").type(JsonFieldType.NUMBER).description("전통주 아이디"),
                                fieldWithPath("body.data.alcoholName").type(JsonFieldType.STRING).description("전통주 이름"),
                                fieldWithPath("body.data.abv").type(JsonFieldType.STRING).description("도수"),
                                fieldWithPath("body.data.specification").type(JsonFieldType.STRING).description("규격"),
                                fieldWithPath("body.data.manufacturer").type(JsonFieldType.STRING).description("제조사"),
                                fieldWithPath("body.data.mainIngredient").type(JsonFieldType.STRING).description("주원료")
                        )
                ));
    }
}
