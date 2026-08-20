package com.TestCaseGenerator.service;

import com.TestCaseGenerator.dto.AIProblemDTO;
import com.TestCaseGenerator.dto.TestCase;
import com.TestCaseGenerator.dto.TestCaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Service
public class AIGenerationService {

    @Value("${GEMINI_API_KEY}")
    private String geminiApiKey;

    public TestCaseResponse generateTestCases(AIProblemDTO request) {
        HttpClient httpClient = HttpClient.newHttpClient();

        String prompt = String.format("""
            You are a test case generator.

            Given this problem:
            %s

            Generate exactly %d test cases in raw JSON format.

            Each test case should be:
            - A JSON object with `"input"` and `"output"` fields.
            - `"input"` must contain actual concrete values, like numbers, strings, arrays, maps, etc. No placeholders like `empty: false` or `mapType`.
            - `"output"` must be the correct answer based on the input.

            STRICT FORMAT:
            [
              { "input": { "arr": [4, 6, 8] }, "output": 8 },
              { "input": { "arr": [1, 3, 5] }, "output": -1 }
            ]

            ONLY return the JSON array. Do not explain anything.
            Language context: %s
            """, request.getProblemStatement(), request.getNumberOfTestCases(), request.getLanguage());

        JSONObject payload = new JSONObject()
                .put("contents", new JSONArray()
                        .put(new JSONObject()
                                .put("parts", new JSONArray()
                                        .put(new JSONObject().put("text", prompt))
                                )
                        )
                );

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + geminiApiKey))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Gemini Raw Response: " + response.body());

            JSONObject responseJson = new JSONObject(response.body());
            JSONArray candidates = responseJson.getJSONArray("candidates");

            if (candidates.isEmpty()) {
                throw new RuntimeException("No response candidates from Gemini.");
            }

            String rawContent = candidates.getJSONObject(0)
                    .getJSONObject("content")
                    .getJSONArray("parts")
                    .getJSONObject(0)
                    .getString("text")
                    .trim();

            System.out.println("Raw Gemini Response Content:\n" + rawContent);

            JSONArray jsonArray = parseTestCasesFromGemini(rawContent);

            System.out.println("Parsed JSON Array:\n" + jsonArray);

            List<TestCase> testCases = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                TestCase testCase = new TestCase();

                // Convert JSONObject to Map<String, Object>
                JSONObject inputJson = item.getJSONObject("input");
                Map<String, Object> inputMap = objectMapper.readValue(inputJson.toString(), Map.class);
                testCase.setInput(inputMap);

                testCase.setOutput(item.get("output"));
                testCases.add(testCase);
            }

            System.out.println("TestCases after storing:\n" + testCases);
            TestCaseResponse testCaseResponse = new TestCaseResponse();
            testCaseResponse.setTestCases(testCases);
            System.out.println("Returning TestCaseResponse: " + objectMapper.writeValueAsString(testCaseResponse));
            return testCaseResponse;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Gemini API call failed: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Gemini response: " + e.getMessage());
        }
    }

    private JSONArray parseTestCasesFromGemini(String rawText) throws Exception {
        String cleanedJson = rawText
                .replace("```json", "")
                .replace("```", "")
                .trim();

        int i = cleanedJson.indexOf("[");
        int j = cleanedJson.lastIndexOf("]");
        if (i != -1 && j != -1) {
            cleanedJson = cleanedJson.substring(i, j + 1);
        }

        return new JSONArray(cleanedJson);
    }
}