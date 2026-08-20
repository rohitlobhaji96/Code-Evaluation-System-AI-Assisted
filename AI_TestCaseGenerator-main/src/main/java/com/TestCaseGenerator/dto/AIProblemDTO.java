package com.TestCaseGenerator.dto;
import lombok.Data;


@Data
public class AIProblemDTO {

    private String problemStatement;
    private String language;
    private int numberOfTestCases;

}
