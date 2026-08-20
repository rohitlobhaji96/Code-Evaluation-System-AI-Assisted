package com.ProgressTracking.controller;


import com.ProgressTracking.reposneDto.LeaderBoardEntryDto;
import com.ProgressTracking.service.ProgressTrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/progress")
@RequiredArgsConstructor
public class ProgressTrackingController {
    private final ProgressTrackingService trackingService;

    @GetMapping("/summary/{userId}")
    public ResponseEntity<?> getSummary( @PathVariable  Long userId){
        return ResponseEntity.ok(trackingService.getUserSummary(userId));
    }

    @GetMapping("/{userId}/submissions")
    public ResponseEntity<?> getSubmissions(@PathVariable Long userId){
        return ResponseEntity.ok(trackingService.getSubmissionHistory(userId));
    }

    @GetMapping("/leaderboard")
    public List<LeaderBoardEntryDto> getLeaderboard() {
        return trackingService.getLeaderBoard();
    }


}
