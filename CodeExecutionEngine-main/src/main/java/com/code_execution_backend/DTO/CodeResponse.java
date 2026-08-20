package com.code_execution_backend.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CodeResponse {
    private String output;
    private String status;
    private String time;
    private int memory;
    private String stderr;
}
