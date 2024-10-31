package com.lilac.soaback.global.data.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lilac.soaback.alcohol.repository.KoreanAlcoholRepository;
import com.lilac.soaback.brewery.repository.BreweryRepository;
import com.lilac.soaback.entity.Brewery;
import com.lilac.soaback.entity.BreweryTourist;
import com.lilac.soaback.entity.KoreanAlcohol;
import com.lilac.soaback.global.data.CsvReadService;
import com.lilac.soaback.global.data.DataInsertService;
import com.lilac.soaback.region.exception.NotFoundRegionException;
import com.lilac.soaback.region.repository.RegionRepository;
import com.lilac.soaback.tourist.repository.TouristRepository;
import com.lilac.soaback.type.exception.NotFoundTypeException;
import com.lilac.soaback.type.repository.TypeRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Getter
@Transactional
public class DataInsertServiceImpl implements DataInsertService {
    private final KoreanAlcoholRepository koreanAlcoholRepository;
    private final RegionRepository regionRepository;
    private final BreweryRepository breweryRepository;
    private final TypeRepository typeRepository;
    private final TouristRepository touristRepository;

    private final CsvReadService csvReadService;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    /**
     *
     * 파일 안에 들어있는 데이터를 insert
     */
    public void dataAllInsert(String filePath){
        List<String[]> dataList = csvReadService.readCsv(filePath);
        for(int i=1; i< dataList.size(); i++){

            dataInsert(dataList.get(i));
        }
    }

    /**
     *
     * 데이터 한건씩 넣기
     *
     * @param list 데이터의 한 셋트
     */
    public void dataInsert(String[] list){
        List<Double> latLon = searchLatLon(list[0]);
        Brewery brewery = new Brewery(list[0], list[2], list[3], list[4], list[5], list[16], list[17], latLon.get(0), latLon.get(1));
        breweryRepository.save(brewery);

        brewery.setRegion(regionRepository.findByName(list[1]).orElseThrow(
                () -> new NotFoundRegionException(list[1] + "의 지역을 찾을 수 없습니다.")));

        for(int i=7; i<=11; i=i+2){
            if(list[i] != null && !list[i].isEmpty()){
                addAlcohol(brewery, list[i], list[i+1]);
            }
        }

        for(int i=13; i<=15; i++){
            if(Objects.nonNull(list[i])){
                addTourist(brewery, list[i]);
            }
        }
    }

    /**
     * 관련 여행지 넣기
     *
     * @param brewery 양조장
     * @param tourist 여행지
     */
    private void addTourist(Brewery brewery, String tourist){
        String content = tourist.replaceAll("\\(.*\\)", "").trim();
        String type = tourist.replaceAll(".*\\((.*)\\).*", ""); // 괄호 안의 값 추출

        BreweryTourist breweryTourist = new BreweryTourist(content, type);
        touristRepository.save(breweryTourist);

        brewery.addBreweryTourist(breweryTourist);
    }

    /**
     *
     * 주력제품 넣기
     *
     * @param brewery 속한 양조장
     * @param nameType 이름과 타입
     * @param abv 도수
     */
    private void addAlcohol(Brewery brewery, String nameType, String abv){

        String name = nameType.replaceAll("\\(.*\\)", "").trim();
        String type = nameType.replaceAll(".*\\((.*)\\).*", "$1"); // 괄호 안의 값 추출

        if(Objects.isNull(abv)){
            abv = "0%";
        }

        KoreanAlcohol koreanAlcohol = new KoreanAlcohol(name, abv);
        koreanAlcoholRepository.save(koreanAlcohol);
        log.info(nameType +" 지금의 nameType 그리고 abv : " + abv );
        koreanAlcohol.setType(typeRepository.findByName(type).orElseThrow(
                () -> new NotFoundTypeException(type + "이 없습니다.")));

        brewery.addAlcohol(koreanAlcohol);
    }

    /**
     *
     * 양조장 이름으로 위도 경도 찾기(네이버 API 이용)
     *
     * @param breweryName 양조장의 이름
     * @return 위도와 경도
     */
    private List<Double> searchLatLon(String breweryName){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "XiGx4tApnNE4U_EgJpBm");
        headers.add("X-Naver-Client-Secret", "WZfi0TUmun");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("https://openapi.naver.com/v1/search/local.json?query=" + breweryName, HttpMethod.GET, entity, String.class);

        List<Double> latLon = new ArrayList<>();
        try {
            // JSON 데이터를 JsonNode 객체로 변환
            JsonNode root = objectMapper.readTree(response.getBody());

            // items 배열에서 첫 번째 객체 가져오기
            JsonNode firstItem = root.path("items").get(0);

            // mapx 와 mapy 값 추출
            latLon.add(Double.parseDouble(firstItem.path("mapy").asText())/10000000);
            latLon.add(Double.parseDouble(firstItem.path("mapx").asText())/10000000);


        } catch (Exception e) {
            log.error("{}의 주소를 찾을 수 없습니다. {}", breweryName, e.getMessage());
        }
        return latLon;
    }
}
