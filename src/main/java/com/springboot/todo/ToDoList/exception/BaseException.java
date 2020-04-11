package com.springboot.todo.ToDoList.exception;

public class BaseException extends RuntimeException {

    BaseException(String code){
        super(code);
    }
}
