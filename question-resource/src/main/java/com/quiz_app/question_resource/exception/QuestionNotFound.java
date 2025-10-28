package com.quiz_app.question_resource.exception;

public class QuestionNotFound extends RuntimeException {
    public QuestionNotFound(String message) {
        super(message);
    }
}
