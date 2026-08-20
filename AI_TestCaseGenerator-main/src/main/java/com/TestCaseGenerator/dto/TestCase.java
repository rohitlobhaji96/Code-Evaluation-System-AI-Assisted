package com.TestCaseGenerator.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestCase {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> input;
    private Object output;

}
