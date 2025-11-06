package com.quiz_app.user_resource.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex, WebRequest req){
    ApiError error = new ApiError(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            ex.getMessage(),
            req.getDescription(false).replace("uri=","")
    );

    return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
}

    @ExceptionHandler(ContentNotFoundException.class)
    public ResponseEntity<ApiError> handleQuestionNotFound(ContentNotFoundException ex, WebRequest req){
        ApiError error = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                req.getDescription(false).replace("uri=","")
        );

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
