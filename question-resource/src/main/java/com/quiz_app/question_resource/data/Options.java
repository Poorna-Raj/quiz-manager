package com.quiz_app.question_resource.data;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_question_options")
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private long id;

    @Column(name = "option")
    private String option;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
