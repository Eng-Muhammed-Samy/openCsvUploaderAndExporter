package com.export.exportfilecsv.models;

import lombok.Data;

import java.util.List;

@Data
public class PairResult<T, R> {
    List<T> successRows;
    List<R> failureRows;

}
