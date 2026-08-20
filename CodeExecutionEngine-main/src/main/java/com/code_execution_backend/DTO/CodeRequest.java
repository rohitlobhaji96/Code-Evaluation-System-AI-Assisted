package com.code_execution_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeRequest {

    private String sourceCode;
    private Integer languageId;
    private String input;

}
