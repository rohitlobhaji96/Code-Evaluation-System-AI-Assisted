package com.TestCaseGenerator.controller;


import com.TestCaseGenerator.dto.AIProblemDTO;
import com.TestCaseGenerator.dto.TestCaseResponse;
import com.TestCaseGenerator.service.AIGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/testcases")
@RequiredArgsConstructor
public class TestCaseController {

    private final AIGenerationService testCaseService;

    @PostMapping("/generate")
    public ResponseEntity<TestCaseResponse> generateTestCase(@RequestBody AIProblemDTO requestDTO) {
        TestCaseResponse responseDTO = testCaseService.generateTestCases(
                requestDTO
        );
        return ResponseEntity.ok(responseDTO);
    }
}
