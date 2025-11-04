package com.quiz_app.result_resource.data;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private long id;
    @Column(name = "quiz_id")
    private long quizId;
    @Lob
    @Column(name = "answers",columnDefinition = "TEXT")
    private String answers;
    @Column(name = "score")
    private double score;
    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt() {
        this.submittedAt = LocalDateTime.now();
    }
}
