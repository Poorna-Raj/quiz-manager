package com.quiz_app.quiz_service.data.model;

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

    @ManyToOne
    @JoinColumn(name = "question_list_id")
    private QuestionList questionList;

    @Column(name = "quiz_name", unique = true)
    private String name;

    @Column(name = "question_count")
    private int questionCount;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    @Column(name = "status")
    private String status = "ACTIVE";

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;
}
