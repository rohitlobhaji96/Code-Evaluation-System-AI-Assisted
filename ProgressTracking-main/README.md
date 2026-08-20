
# 📊 Progress Tracking API

This Spring Boot application provides APIs for tracking coding progress, including user summaries, submission history, and a leaderboard. It is designed for use in online coding platforms to evaluate and track user performance over time.

---
```
## 📁 Project Structure
com.ProgressTracking
├── controller
│   └── ProgressTrackingController.java
├── model
│   ├── Submission.java
│   └── UserProgress.java
├── repo
│   ├── SubmissionRepo.java
│   └── UserProgressRepo.java
├── reponseDto
│   ├── LeaderBoardEntryDto.java
│   ├── ProgressSummary.java
│   └── SubmissionHistory.java
├── requestDto
│   ├── SubmissionRequest.java
│   └── UserProgressRequest.java
├── service
│   └── ProgressTrackingService.java
└── ProgressTrackingApplication.java
```
---

## 🌐 API Endpoints

Base Path: `/api/progress`

### ✅ 1. Get User Progress Summary

- **URL**: `/api/progress/summary/{userId}`
- **Method**: `GET`
- **Description**: Returns a summary of the user's total score, problems solved, and rank.
- **Response**: `ProgressSummary`

---

### 📜 2. Get Submission History

- **URL**: `/api/progress/{userId}/submissions`
- **Method**: `GET`
- **Description**: Returns all submission details by a user.
- **Response**: `List<SubmissionHistory>`

---

### 🏆 3. Get Leaderboard

- **URL**: `/api/progress/leaderboard`
- **Method**: `GET`
- **Description**: Returns a ranked list of top users based on performance.
- **Response**: `List<LeaderBoardEntryDto>`

---

## 📷 Postman Testing Screenshots

### 1. Get Summary API Test
![Summary](https://github.com/user-attachments/assets/3e848c32-2eca-4cc0-a1c9-6eb5c1d4721f)

### 2. Get Submission History API Test
![Submission History]((https://github.com/user-attachments/assets/1146eff7-fa5c-4563-8853-b78af3e98122))

### 3. Get Leaderboard API Test
![Leaderboard](https://github.com/user-attachments/assets/7c3a5409-811c-4483-9716-baac543ce3b7)

