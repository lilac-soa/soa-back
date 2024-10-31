package com.lilac.soaback.global.data;

import java.util.List;

public interface CsvReadService {

    List<String[]> readCsv(String fileName);
}
