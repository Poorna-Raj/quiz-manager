package com.quiz_app.quiz_resource.data;

import java.time.LocalDateTime;
import java.util.List;

public class QuizResponseDto {
    private long id;
    private long questionListId;
    private int questionCount;
    private String createdBy;
    private LocalDateTime createdAt;
    private List<QuizQuestionResponseDto> questions;
    private String status;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuestionListId() {
        return questionListId;
    }

    public void setQuestionListId(long questionListId) {
        this.questionListId = questionListId;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<QuizQuestionResponseDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuizQuestionResponseDto> questions) {
        this.questions = questions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
