package com.springboot.todo.ToDoList.exception;

public class BoardNotFoundException extends BaseException {

    public BoardNotFoundException() {
        super(ExceptionCode.BOARD_NOT_FOUND);
    }
}