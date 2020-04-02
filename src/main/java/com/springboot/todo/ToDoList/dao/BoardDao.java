package com.springboot.todo.ToDoList.dao;

import com.springboot.todo.ToDoList.model.Board;

import java.util.List;
import java.util.Optional;

public interface BoardDao {

    Board addBoard(Board board);

    List<Board> getAllBoardsByUser(String userID, int page, int pageSize, String sortBy);

    Optional<Board> getBoardById(String id);

    void updateBoard(String id, Board updatedBoard);

    void deleteBoard(String id);
}
