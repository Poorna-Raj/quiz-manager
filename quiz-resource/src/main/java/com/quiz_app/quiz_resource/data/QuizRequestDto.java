package com.quiz_app.quiz_resource.data;

public class QuizRequestDto {
    private long questionListId;
    private int questionCount;
    private String createdBy;
    private String name;
    private String description;
    private String type;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
