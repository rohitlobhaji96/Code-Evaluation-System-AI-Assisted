package com.code_execution_backend.controller;

import com.code_execution_backend.DTO.CodeRequest;
import com.code_execution_backend.service.CodeExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/code")
@RequiredArgsConstructor
public class CodeExecutionController {

    private final CodeExecutionService codeExecutionService;

    @PostMapping("/execution")
    public ResponseEntity<?> runCode(@RequestBody CodeRequest request){
        return ResponseEntity.ok(codeExecutionService.executeCode(request));
    }
}
