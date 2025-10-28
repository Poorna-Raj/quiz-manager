package com.quiz_app.question_resource.data;

import java.util.List;

public class QuestionRequestDto {
    private String question;
    private List<String> options;
    private String answer;
    private int marks;

    public QuestionRequestDto(String question, List<String> options, String answer, int marks) {
        this.question = question;
        this.options = options;
        this.answer = answer;
        this.marks = marks;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
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
