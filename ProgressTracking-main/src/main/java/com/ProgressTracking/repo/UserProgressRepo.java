package com.ProgressTracking.repo;

import com.ProgressTracking.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProgressRepo extends JpaRepository<UserProgress,Long> {
}
