package com.springboot.todo.ToDoList.service;

import com.springboot.todo.ToDoList.dao.BoardDao;
import com.springboot.todo.ToDoList.dao.UserDao;
import com.springboot.todo.ToDoList.model.Board;
import com.springboot.todo.ToDoList.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardDao boardDao;

    @Autowired
    public BoardService(@Qualifier("Boards") BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public Board addBoard(Board board){
       return boardDao.addBoard(board);
    }

    public List<Board> getAllBoardsByUser(String userID, int page, int pageSize, String sortBy){
        return boardDao.getAllBoardsByUser(userID, page, pageSize, sortBy);
    }

    public Optional<Board> getBoardById(String id){
        return boardDao.getBoardById(id);
    }

    public Board updateBoard(String id, Board updatedBoard){
        boardDao.updateBoard(id,updatedBoard);
        Optional<Board> boardOp = boardDao.getBoardById(id);
        Board board = boardOp.get();
        return board;
    }

    public void deleteBoard(String id){
        boardDao.deleteBoard(id);
    }
}
