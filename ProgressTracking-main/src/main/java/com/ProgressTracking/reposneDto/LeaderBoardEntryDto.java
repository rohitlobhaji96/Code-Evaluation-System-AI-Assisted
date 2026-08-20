package com.ProgressTracking.reposneDto;

import lombok.Data;

@Data
public class LeaderBoardEntryDto {

    private String username;
    private int rank;
    private int problemsSolved;
}
