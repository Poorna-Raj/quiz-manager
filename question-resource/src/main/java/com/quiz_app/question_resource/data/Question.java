package com.quiz_app.question_resource.data;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tbl_question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private long id;
    @Column(name = "question")
    private String question;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Options> options;

    @Column(name = "answer")
    private String answer;

    @Column(name = "marks")
    private int marks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Options> getOptions() {
        return options;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
