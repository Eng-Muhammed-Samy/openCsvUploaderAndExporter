package com.export.exportfilecsv.utils;

import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public final class OpenCsvExporter<T> {
    public void export(List<T> list, PrintWriter writer) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        writer.append( buildHeader( list.get(0) ));
        StatefulBeanToCsv<T> beanWriter = new StatefulBeanToCsvBuilder<T>(writer)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(true)
                .build();
        beanWriter.write( list );
    }

    private String buildHeader(T clazz) {
        return Arrays.stream( clazz.getClass().getDeclaredFields() )
                .filter( f ->
                        f.getAnnotation( CsvBindByPosition.class ) != null &&
                                f.getAnnotation( CsvBindByName.class ) != null
                )
                .sorted( Comparator.comparing(f -> f.getAnnotation( CsvBindByPosition.class ).position() ) )
                .map( f -> f.getAnnotation( CsvBindByName.class ).column() )
                .collect( Collectors.joining( "," ) ) + "\n";
    }
}
