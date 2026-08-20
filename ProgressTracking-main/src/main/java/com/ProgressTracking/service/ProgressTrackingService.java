package com.ProgressTracking.service;


import com.ProgressTracking.model.Submission;
import com.ProgressTracking.model.UserProgress;
import com.ProgressTracking.repo.SubmissionRepo;
import com.ProgressTracking.repo.UserProgressRepo;
import com.ProgressTracking.reposneDto.LeaderBoardEntryDto;
import com.ProgressTracking.reposneDto.ProgressSummary;
import com.ProgressTracking.reposneDto.SubmissionHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressTrackingService {

    private final UserProgressRepo userProgressRepo;
    private final SubmissionRepo submissionRepo;

    public ProgressSummary  getUserSummary(Long userId){
        long totalSolved = submissionRepo.countByUserIdAndStatus(userId , "Accepted");
        long totalAttempts = submissionRepo.countByUserId(userId);
        long solvedEasy = submissionRepo.countByUserIdAndDifficultyAndStatus(userId , "Easy" , "Accepted");
        long solvedMedium = submissionRepo.countByUserIdAndDifficultyAndStatus(userId , "Medium" , "Accepted");
        long solvedHard = submissionRepo.countByUserIdAndDifficultyAndStatus(userId , "Difficulty" , "Accepted");

        List<Submission> submissionList = submissionRepo.findByUserIdOrderBySubmittedAtDesc(userId);
        int streak =0;
        LocalDate prev = null;
        for (Submission s : submissionList){
            LocalDate subDate = s.getSubmittedAt().toLocalDate();
            if (prev == null || prev.minusDays(1).equals(subDate)) {
                streak++;
                prev = subDate;
            } else if (!prev.equals(subDate)) {
                break;
            }
        }

        ProgressSummary progressSummary = new ProgressSummary();
        progressSummary.setSolvedEasy((int) solvedEasy);
        progressSummary.setSolvedMedium((int) solvedMedium);
        progressSummary.setSolvedHard((int) solvedHard);
        progressSummary.setTotalAttempts((int) totalAttempts);
        progressSummary.setTotalSolved((int) totalSolved);
        progressSummary.setCurrentStreakRate(streak);
        progressSummary.setSuccessRate(totalAttempts > 0 ? 100.0 * totalSolved / totalAttempts : 00.0);
        return progressSummary;
    }

    public List<SubmissionHistory> getSubmissionHistory(Long userId){
        List<Submission> submissions = submissionRepo.findByUserIdOrderBySubmittedAtDesc(userId);
        List<SubmissionHistory> result = new ArrayList<>();
        for (Submission s : submissions) {
            SubmissionHistory dto = new SubmissionHistory();
            dto.setProblemTitle(s.getProblemTitle());
            dto.setStatus(s.getStatus());
            dto.setLanguage(s.getLanguage());
            dto.setSubmittedAt(s.getSubmittedAt().toString());
            dto.setDifficulty(s.getDifficulty());
            result.add(dto);
        }
        return result;
    }

    public List<LeaderBoardEntryDto> getLeaderBoard (){
        List<UserProgress> all = userProgressRepo.findAll();
        all.sort(Comparator.comparingInt(UserProgress::getTotalSolved).reversed());
        List<LeaderBoardEntryDto> leaderBoardList = new ArrayList<>();
        int rank = 1;
        for (UserProgress up : all) {
            LeaderBoardEntryDto dto = new LeaderBoardEntryDto();
            dto.setUsername("User " +up.getUserId());
            dto.setProblemsSolved(up.getTotalSolved());
            dto.setRank(rank++);
            leaderBoardList.add(dto);
        }
        return leaderBoardList;
    }


}
