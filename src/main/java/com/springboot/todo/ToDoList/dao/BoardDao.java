package com.springboot.todo.ToDoList.dao;

import com.springboot.todo.ToDoList.model.Board;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface BoardDao {

    Board addBoard(Board board);

    List<Board> getAllBoardsByUser(String userID, int page, int pageSize, String sortBy);

    Optional<Board> getBoardById(ObjectId id);

    void updateBoard(ObjectId id, Board updatedBoard);

    void deleteBoard(ObjectId id);
}
