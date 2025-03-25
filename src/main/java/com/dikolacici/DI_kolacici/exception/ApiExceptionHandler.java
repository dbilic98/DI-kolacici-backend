package com.dikolacici.DI_kolacici.exception;

import com.dikolacici.DI_kolacici.exception.response.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CategoryNotFoundException.class})

    public ResponseEntity<Object> handleNotFoundException(Exception e, WebRequest request) {
        List<String> messages = new ArrayList<>();
        messages.add(e.getMessage());
        ErrorResponse body = new ErrorResponse(messages);
        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorResponse> handleViolationException(RuntimeException ex) {
        List<String> messages = new ArrayList<>();
        messages.add("You cannot delete this record because it is linked other records in the system.");
        ErrorResponse body = new ErrorResponse(messages);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
