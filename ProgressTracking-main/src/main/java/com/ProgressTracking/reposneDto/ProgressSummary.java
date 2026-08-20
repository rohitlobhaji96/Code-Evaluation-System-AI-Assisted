package com.ProgressTracking.reposneDto;


import lombok.Data;

@Data
public class ProgressSummary {

    private int totalSolved;
    private int totalAttempts;
    private int solvedEasy;
    private int solvedMedium;
    private int solvedHard;
    private double successRate;
    private double currentStreakRate;

}
