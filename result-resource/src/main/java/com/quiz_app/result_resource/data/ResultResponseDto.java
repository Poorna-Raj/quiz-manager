package com.quiz_app.result_resource.data;

import java.time.LocalDateTime;
import java.util.List;

public class ResultResponseDto {
    private long id;
    private long quizId;
    private List<ResultQuestionResponseDto> answers;
    private double score;
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

    public List<ResultQuestionResponseDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ResultQuestionResponseDto> answers) {
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

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}
