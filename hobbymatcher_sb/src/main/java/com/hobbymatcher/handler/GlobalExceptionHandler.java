package com.hobbymatcher.handler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST) // status-> 400
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public String paramExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult exceptions = e.getBindingResult();
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                FieldError fieldError = (FieldError) errors.get(0);
                return fieldError.getDefaultMessage();
            }
        }
        return "params wrong";
    }
}