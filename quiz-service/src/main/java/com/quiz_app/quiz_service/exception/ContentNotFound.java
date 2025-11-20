package com.quiz_app.quiz_service.exception;

public class ContentNotFound extends RuntimeException {
    public ContentNotFound(String message) {
        super(message);
    }
}
