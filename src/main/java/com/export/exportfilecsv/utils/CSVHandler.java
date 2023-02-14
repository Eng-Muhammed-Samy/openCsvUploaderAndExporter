package com.export.exportfilecsv.utils;

import com.export.exportfilecsv.converter.CsvConverter;
import com.export.exportfilecsv.models.PairResult;
import com.export.exportfilecsv.models.ResultModel;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CSVHandler<T> {
    private final CsvConverter<T> converter;
    private  CsvToBean<T> readFile(Class<T> className, Reader reader) throws IOException {
          return new CsvToBeanBuilder<T>(reader)
                    .withType(className)
                    .withIgnoreEmptyLine(true)
                    .withOrderedResults(true)
                    .withMappingStrategy(buildMappingHeader(className))
                    .withThrowExceptions(false)
                    .build();
    }
    public PairResult<T, CsvException> getValidData(MultipartFile file, Class<T> className) throws IOException {
        PairResult<T, CsvException> result = null;
        try {
            Reader reader = new InputStreamReader(file.getInputStream());
            CsvToBean<T> validData = readFile(className, reader);
             result = new PairResult<>();
             result.setSuccessRows(validData.parse());
             List<CsvException> exceptions = validData.getCapturedExceptions();
             result.setFailureRows(exceptions);
            reader.close();
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }

        return result;
    }

    public List<T> wrapper(List<T> validData, List<T> invalidData) {
        List<T> wrapperList = new ArrayList<>();
        wrapperList.addAll(validData);
        wrapperList.addAll(invalidData);
        return wrapperList.stream().sorted((a1, a2) -> a1.toString().compareTo(a2.toString())).collect(Collectors.toList());
    }

    public List<T> convertToBean(List<CsvException> exceptions){
        return exceptions.stream().map(exception -> converter.convert(exception.getLine())).collect(Collectors.toList());
    }


    private MappingStrategy<T> buildMappingHeader(Class<T> clazz) {
        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(clazz);
        return strategy;
    }


}
