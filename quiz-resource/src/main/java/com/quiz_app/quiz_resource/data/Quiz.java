package com.quiz_app.quiz_resource.data;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private long id;
    @Column(name = "question_list_id")
    private long questionListId;
    @Column(name = "quiz_name", unique = true)
    private String name;
    @Column(name = "question_count")
    private int questionCount;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<QuizQuestion> questions = new ArrayList<>();

    @Column(name = "status")
    private String status = "ACTIVE";
    @Column(name = "description")
    private String description;
    @Column(name = "type")
    private String type;

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

    public List<QuizQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuizQuestion> questions) {
        this.questions = questions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
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
