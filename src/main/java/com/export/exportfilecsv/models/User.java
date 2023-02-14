package com.export.exportfilecsv.models;

import com.opencsv.bean.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends ResultModel{
//    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "ID", required = true)
    private Long id;

    @CsvBindByName(column = "Full Name", required = true)
//    @CsvBindByPosition(position = 1)
    private String name;

    @CsvBindByName(column = "Email Address", required = false)
//    @CsvBindByPosition(position = 2)
    private String email;

    @CsvBindByName(column = "Phone Number", required = false)
//    @CsvBindByPosition(position = 3)
    private String phone;

    @CsvBindByName(column = "Address", required = true)
//    @CsvBindByPosition(position = 4)
    private String address;

    @CsvBindByName(column = "Salary", required = false)
//    @CsvBindByPosition(position = 5)
    private Double salary;

    @CsvBindByName(column = "Valid", required = false)
//    @CsvBindByPosition(position = 6)
    private Boolean isValid;

    @CsvBindByName(column = "Raison", required = false)
//    @CsvBindByPosition(position = 7)
    private String raison;

    @CsvBindByName(column = "CIF", required = false)
    private String cif;


}
