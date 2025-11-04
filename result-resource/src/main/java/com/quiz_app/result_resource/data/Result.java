package com.quiz_app.result_resource.data;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private long id;
    @Column(name = "quiz_id")
    private long quizId;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ResultQuestion> answers = new ArrayList<>();
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

    public List<ResultQuestion> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ResultQuestion> answers) {
        this.answers = answers;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
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
