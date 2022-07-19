package com.github.gbaso.examples.csv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import com.opencsv.ICSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CsvUtils {

    public <T> Stream<T> getRows(Class<T> type, Path path, Character separator) throws IOException {
        try (var reader = Files.newBufferedReader(path)) {
            CsvToBeanBuilder<T> builder = new CsvToBeanBuilder<>(reader);
            builder.withType(type);
            if (separator != null) {
                builder.withSeparator(separator);
            }
            CsvToBean<T> beanReader = builder.build();
            return beanReader.stream();
        }
    }

    public <T> void writeRows(List<T> rows, Class<T> type, Path file) throws CsvException, IOException {
        try (var writer = Files.newBufferedWriter(file)) {
            HeaderColumnNameMappingStrategy<T> mappingStrategy = new HeaderColumnNameMappingStrategy<>();
            mappingStrategy.setType(type);
            StatefulBeanToCsv<T> beanWriter = new StatefulBeanToCsvBuilder<T>(writer)
                    .withMappingStrategy(mappingStrategy)
                    .withLineEnd(ICSVWriter.RFC4180_LINE_END)
                    .build();
            beanWriter.write(rows);
        }
    }

}
