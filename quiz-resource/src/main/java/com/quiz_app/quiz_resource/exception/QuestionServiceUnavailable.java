package com.quiz_app.quiz_resource.exception;

public class QuestionServiceUnavailable extends RuntimeException {
    public QuestionServiceUnavailable(String message,Throwable cause) {
        super(message,cause);
    }
}
