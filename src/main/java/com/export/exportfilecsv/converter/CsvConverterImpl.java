package com.export.exportfilecsv.converter;


import com.export.exportfilecsv.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CsvConverterImpl implements CsvConverter<User>{

    @Override
    public User convert(String[] arr) {
        return new User(Long.parseLong(arr[0]), arr[1], arr[2], arr[3], arr[4],
                        Double.parseDouble(arr[5]) ,
                        Boolean.parseBoolean(arr[6]), arr[7], arr[8]);
    }
}
