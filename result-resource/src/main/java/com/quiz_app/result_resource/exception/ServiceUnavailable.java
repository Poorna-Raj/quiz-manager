package com.quiz_app.result_resource.exception;

public class ServiceUnavailable extends RuntimeException {
    public ServiceUnavailable(String message) {
        super(message);
    }
}
