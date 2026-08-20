# 🧪 Test Case Generator API (Powered by Gemini AI)

This Spring Boot application uses **Google Gemini API** to automatically generate intelligent and diverse test cases based on any programming problem statement.

---

## 📂 Folder Structure
src
└── main
    └── java
        └── com
            └── TestCaseGenerator
                ├── controller
                │   └── TestCaseController.java
                ├── dto
                │   ├── AIProblemDTO.java
                │   ├── TestCase.java
                │   └── TestCaseResponse.java
                ├── service
                │   └── AIGenerationService.java
                └── TestCaseGeneratorApplication.java
---

## 🌐 API Endpoint

### `POST /generate`

Generates AI-powered test cases based on the problem description.

#### ✅ Request Body

```json
{
  "problemStatement": "Given an array, return the max even number. If none, return -1.",
  "numberOfTestCases": 3,
  "language": "Java"
}
```
## Response 
```json
{
  "testCases": [
    {
      "input": [1, 2, 3, 4],
      "output": 4
    },
    {
      "input": [7, 5, 1],
      "output": -1
    },
    {
      "input": [10, 20, 3],
      "output": 20
    }
  ]
}
```
---
## How It Works
- Receives a problem statement via REST API.
- Builds a prompt for Gemini 2.0 Flash model.
- Parses Gemini’s JSON array response into structured test cases.
- Returns the test cases to the frontend/client.

---

# Demo Video 
(https://drive.google.com/file/d/1SIp1MJtnlWCVypP9tnlFMh4Bugzqq__y/view?usp=sharing)

---
