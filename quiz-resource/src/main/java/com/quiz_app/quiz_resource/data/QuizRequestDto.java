package com.quiz_app.quiz_resource.data;

public class QuizRequestDto {
    private long questionListId;
    private int questionCount;
    private String createdBy;

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
}
