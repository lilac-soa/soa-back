package com.lilac.soaback.global.data.impl;

import com.lilac.soaback.global.data.CsvReadService;
import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CsvReadServiceImpl implements CsvReadService {

    public List<String[]> readCsv(String fileName) {
        List<String[]> records = new ArrayList<>();
        Resource resource = new ClassPathResource(fileName);

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(values);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return records;
    }
}
