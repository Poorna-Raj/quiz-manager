package com.quiz_app.user_resource.UserException;

public class ContentNotFound extends RuntimeException {
    public ContentNotFound(String message) {
        super(message);
    }
}
