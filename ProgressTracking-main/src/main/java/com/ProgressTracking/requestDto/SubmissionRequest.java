package com.ProgressTracking.requestDto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubmissionRequest {
    private long userId;
    private String problemTitle;
    private String status;
    private String language;
    private String difficulty;
    private LocalDateTime submittedAt;
}
