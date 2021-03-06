package com.springboot.todo.ToDoList.util;

import com.springboot.todo.ToDoList.viewmodel.ErrorResponse;
import com.springboot.todo.ToDoList.viewmodel.MainResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static <T> ResponseEntity<MainResponse<T>> data(T data) {

        return new ResponseEntity<>(MainResponse.response(data), HttpStatus.OK);
    }

    public static <T> ResponseEntity<MainResponse<T>> data(T data,HttpStatus httpStatus) {

        return new ResponseEntity<>(MainResponse.response(data), httpStatus);
    }

    public static <T> ResponseEntity<MainResponse<ErrorResponse>> error(String errorMessage, String errorCode) {

        return new ResponseEntity<>(MainResponse.errorResponse(errorMessage, errorCode), HttpStatus.OK);
    }
}