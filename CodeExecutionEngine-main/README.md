# 💻 Code Execution Engine (Spring Boot + Judge0 API)

A backend project built with **Spring Boot** that integrates with the **Judge0 API** to execute source code in multiple languages. This service accepts source code, language ID, and input, and returns the output, execution time, memory used, and any compilation or runtime errors.

---

## 🚀 Features

- Execute code in 60+ programming languages.
- Supports custom standard input (stdin).
- Returns clean output, memory usage, time taken, and error details.
- Decodes Judge0 Base64 responses for frontend-friendly display.
- Cleanly structured with DTOs and REST APIs.

---

## ⚙️ Tech Stack

- Java 17+
- Spring Boot
- RestTemplate (for API integration)
- Judge0 API (via RapidAPI)

---

## 📁 Project Structure

src
└── main
└── java
└── com.code_execution_backend
├── controller
│   └── CodeExecutionController.java
├── service
│   └── CodeExecutionService.java
└── DTO
├── CodeRequest.java
└── CodeResponse.java

---
---

## 🧾 API Endpoints

### `POST /execute`

- **Description:** Executes code using Judge0 and returns output.
- **Request Body (JSON):**
```json
{
  "languageId": 62,
  "sourceCode": "public class Main { public static void main(String[] args) { System.out.println(\"Hello World\"); } }",
  "input": ""
}
```
- **Response Body(JSON):**
```json
{
  "output": "Hello World",
  "status": "Accepted",
  "time": "0.002",
  "memory": 1352,
  "compileOutput": null,
  "stderr": null
}
```
## Judge0 API Integration
- API is called using RestTemplate via RapidAPI.
- Base64 encoding/decoding is handled internally.
- Judge0 endpoint used:
```
https://judge0-ce.p.rapidapi.com/submissions?base64_encoded=true&wait=true
```
- Required headers:
```
X-RapidAPI-Key: <your_api_key>
X-RapidAPI-Host: judge0-ce.p.rapidapi.com
Content-Type: application/json
```
## 🎥 API Demo Video
[Watch on Drive ] (https://drive.google.com/file/d/16XwBTijRFmMrBTtdAKWrCS0wy_tFHYxk/view?usp=drive_link)



