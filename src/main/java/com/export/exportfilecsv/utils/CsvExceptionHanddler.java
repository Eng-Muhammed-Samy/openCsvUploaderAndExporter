package com.export.exportfilecsv.utils;

import com.opencsv.bean.exceptionhandler.CsvExceptionHandler;
import com.opencsv.exceptions.CsvException;

public class CsvExceptionHanddler implements CsvExceptionHandler {
    @Override
    public CsvException handleException(CsvException e) throws CsvException {
        System.out.println(e.getMessage());
        return null;
    }
}
