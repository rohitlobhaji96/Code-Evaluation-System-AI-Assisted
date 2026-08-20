package com.code_execution_backend.service;

import com.code_execution_backend.DTO.CodeRequest;
import com.code_execution_backend.DTO.CodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CodeExecutionService {

//    @Value("X-RapidAPI-Key")
    private  String API_KEY = "52512d94a8msh257aee300962747p168620jsnae083bc65e63";

    private final String JUDGE0_API = "https://judge0-ce.p.rapidapi.com/submissions?base64_encoded=true&wait=true";

    public CodeResponse executeCode(CodeRequest request) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", API_KEY);
        headers.set("X-RapidAPI-Host", "judge0-ce.p.rapidapi.com");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> body = new HashMap<>();
        body.put("language_id", request.getLanguageId());
        body.put("source_code", Base64.getEncoder().encodeToString(request.getSourceCode().getBytes()));
        body.put("stdin", Base64.getEncoder().encodeToString(request.getInput().getBytes()));
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(JUDGE0_API, entity, Map.class);
        Map<String,Object> responseBody = response.getBody();

        assert responseBody != null;

        /**
         * private String output;
         *     private String status;
         *     private String time;
         *     private int memory;
         *     private String stderr;
         *     {
         *     "stdout": "SGVsbG8gZnJvbSBDKysK",
         *     "time": "0.002",
         *     "memory": 1352,
         *     "stderr": null,
         *     "token": "44c46243-3a72-47e0-a82e-10e3e333c53f",
         *     "compile_output": null,
         *     "message": null,
         *     "status": {
         *         "id": 3,
         *         "description": "Accepted"
         *     }
         * }
         *     */

        String stdoutEncoded = (String) responseBody.get("stdout");
        String stdoutDecoded = stdoutEncoded != null
                ? new String(Base64.getDecoder().decode(stdoutEncoded))
                :"";

        String stderrEncoded = (String) responseBody.get("stderr");
        String stderrDecoded = stderrEncoded !=null
                ? new String(Base64.getDecoder().decode(stderrEncoded))
                :" ";

        Map<String , Object > statusMap = (Map<String, Object>) responseBody.get("status");

        return CodeResponse.builder()
                .output(stdoutDecoded)
                .status((String) statusMap.get("description"))
                .time((String)responseBody.get("time"))
                .memory((Integer) responseBody.get("memory"))
                .stderr(stderrDecoded)
                .build();



    }
}
