package com.export.exportfilecsv.models;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class ResultModel {
    @CsvBindByName(column = "Valid", required = false)
     Boolean isValid = Boolean.TRUE;

    @CsvBindByName(column = "Raison", required = false)
     String raison = "";
}
