package com.lilac.soaback.global.data;

/**
 * 데이터 넣기
 *
 * @author 한민기
 *
 */
public interface DataInsertService {

    /**
     * 파일 안에 들어있는 데이터를 insert
     *
     * @param filePath 파일위치
     */
    void dataAllInsert(String filePath);

    /**
     * 데이터 한건씩 넣기
     *
     * @param list 데이터의 한 셋트
     */
     void dataInsert(String[] list);
}
