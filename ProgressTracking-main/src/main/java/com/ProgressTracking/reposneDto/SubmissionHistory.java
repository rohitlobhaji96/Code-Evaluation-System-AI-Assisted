package com.ProgressTracking.reposneDto;

import lombok.Data;

@Data
public class SubmissionHistory {
    private String problemTitle;
    private String status;
    private String language;
    private String submittedAt;
    private String difficulty;
}
