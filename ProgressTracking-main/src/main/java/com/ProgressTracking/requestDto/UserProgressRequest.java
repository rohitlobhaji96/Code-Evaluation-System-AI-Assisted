package com.ProgressTracking.requestDto;

import lombok.Data;

@Data
public class UserProgressRequest {
    private int totalSolved;
    private int totalAttempts;
    private int solvedEasy;
    private int solvedMedium;
    private int solvedHard;
    private int currentStreak;
}
