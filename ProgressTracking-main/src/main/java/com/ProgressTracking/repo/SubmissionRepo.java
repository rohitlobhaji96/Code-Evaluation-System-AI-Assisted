package com.ProgressTracking.repo;

import com.ProgressTracking.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepo extends JpaRepository<Submission,Long> {
    long countByUserIdAndStatus(Long userId, String accepted);

    long countByUserId(Long userId);

    long countByUserIdAndDifficultyAndStatus(Long userId, String easy, String accepted);

    List<Submission> findByUserIdOrderBySubmittedAtDesc(Long userId);
}
