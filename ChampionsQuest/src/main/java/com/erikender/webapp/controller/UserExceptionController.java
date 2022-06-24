package com.erikender.webapp.controller;

import com.erikender.webapp.exceptions.SQLIntegrityConstraintViolationException;
import com.erikender.webapp.exceptions.SQLSyntaxErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionController {
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> exception(SQLIntegrityConstraintViolationException exception) {
        return new ResponseEntity<>("Email already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = SQLSyntaxErrorException.class)
    public ResponseEntity<Object> exception(SQLSyntaxErrorException exception) {
        return new ResponseEntity<>("E-mail must be 50 characters or less, Names must be 30 characters or less.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
