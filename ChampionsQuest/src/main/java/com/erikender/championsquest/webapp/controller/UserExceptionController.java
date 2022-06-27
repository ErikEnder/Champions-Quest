package com.erikender.championsquest.webapp.controller;

import com.erikender.championsquest.webapp.exceptions.SQLIntegrityConstraintViolationException;
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
