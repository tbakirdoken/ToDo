package com.springboot.todo.ToDoList.controller;

import com.springboot.todo.ToDoList.exception.BaseException;
import com.springboot.todo.ToDoList.exception.BaseValidationException;
import com.springboot.todo.ToDoList.exception.ExceptionCode;
import com.springboot.todo.ToDoList.util.ResponseUtil;
import com.springboot.todo.ToDoList.viewmodel.ErrorResponse;
import com.springboot.todo.ToDoList.viewmodel.MainResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    private final MessageSource messageSource;

    @Autowired
    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<MainResponse<ErrorResponse>> handleIllegalArgument(BaseException ex, Locale locale) {

        String errorMessage = messageSource.getMessage(ex.getMessage(), null, locale);
        return ResponseUtil.error(errorMessage, ex.getMessage());
    }

    @ExceptionHandler(BaseValidationException.class)
    public ResponseEntity<MainResponse<ErrorResponse>> validationException(BaseValidationException ex, Locale locale) {

        String errorMessage = messageSource.getMessage(ex.getMessage(), null, locale);
        return ResponseUtil.error(errorMessage, ex.getMessage());

    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<MainResponse<ErrorResponse>> validationException(BindException ex, Locale locale) {
        String errorMessage = ex.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));

        return ResponseUtil.error(errorMessage, ex.getMessage());

    }

    //TODO: ex.getMessage will be deleted
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MainResponse<ErrorResponse>> handleExceptions(Exception ex, Locale locale) {

        return ResponseUtil.error(ExceptionCode.UNEXPECTED_ERROR, ex.getMessage());

    }

}
