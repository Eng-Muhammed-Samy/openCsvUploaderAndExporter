package com.export.exportfilecsv.converter;

public interface CsvConverter<T> {

    T convert(String [] line);
}
