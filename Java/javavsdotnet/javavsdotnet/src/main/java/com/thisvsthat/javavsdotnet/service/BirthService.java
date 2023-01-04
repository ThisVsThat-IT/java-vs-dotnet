package com.thisvsthat.javavsdotnet.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.thisvsthat.javavsdotnet.repository.BirthRepository;
import org.apache.commons.lang3.ClassLoaderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.thisvsthat.javavsdotnet.model.BirthModel;

@Service
public class BirthService {

    @Autowired
    BirthRepository birthRepository;

    public void calculateAndSaveAgeForAll() {
        List<BirthModel> births = parseFile();
        calculateAge(births);

        saveAll(births);
    }

    public List<BirthModel> findAll() {
        return birthRepository.findAll();
    }

    private void saveAll(List<BirthModel> births) {
        birthRepository.saveAll(births);
    }

    private void calculateAge(List<BirthModel> births) {
        births.forEach(b -> b.setAge(Period.between(b.getBirthDate(), LocalDate.now()).getYears()));
    }

    private List<BirthModel> parseFile() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("birthdates.csv");
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        List<BirthModel> births = new ArrayList();
        try (CSVReader reader = new CSVReader(streamReader)) {
            List<String[]> r = reader.readAll();
            r.forEach(x -> births.add(new BirthModel(null, x[0], x[1], LocalDate.parse(x[2], DateTimeFormatter.ofPattern("dd/MM/yyyy")), 0)));
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }

        return births;
    }
}
