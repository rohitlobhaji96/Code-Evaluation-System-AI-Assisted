package com.ProgressTracking.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

import java.time.LocalDateTime;

@Entity
@Data
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private long userId;
    private String problemTitle;
    private String status;
    private String language;
    private String difficulty;
    private LocalDateTime submittedAt;

}
