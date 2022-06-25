package com.erikender.webapp.controller;

import com.erikender.webapp.exceptions.SQLIntegrityConstraintViolationException;
import com.erikender.webapp.exceptions.SQLSyntaxErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionController {
    /**
     * Method that throws exception when user enters non-unique e-mail address on sign up
     * @param exception The exception
     * @return Page telling the user why their input doesn't work.
     */
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public String exception(SQLIntegrityConstraintViolationException exception) {
        return "redirect:/registerfailed";
    }
}
