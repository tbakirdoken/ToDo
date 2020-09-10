package com.springboot.todo.ToDoList.service;

import com.springboot.todo.ToDoList.dao.BoardDao;
import com.springboot.todo.ToDoList.dao.UserDao;
import com.springboot.todo.ToDoList.dto.Response.BoardResponse;
import com.springboot.todo.ToDoList.exception.BoardNotFoundException;
import com.springboot.todo.ToDoList.exception.UserNotFoundException;
import com.springboot.todo.ToDoList.mapper.BoardMapper;
import com.springboot.todo.ToDoList.mapper.UserMapper;
import com.springboot.todo.ToDoList.model.Board;
import com.springboot.todo.ToDoList.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardDao boardDao;

    @Autowired
    public BoardService(@Qualifier("Boards") BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public BoardResponse addBoard(Board board){
        Board newBoard = boardDao.addBoard(board);
        return BoardMapper.map.boardToBoardResponse(newBoard);
    }

    public List<BoardResponse> getAllBoardsByUser(String userID, int page, int pageSize, String sortBy){
        return boardDao.getAllBoardsByUser(userID, page, pageSize, sortBy).stream()
                .map(BoardMapper.map::boardToBoardResponse)
                .collect(Collectors.toList());
    }

    public BoardResponse getBoardById(ObjectId id){
        Board board = boardDao.getBoardById(id).orElseThrow(BoardNotFoundException::new);
        return BoardMapper.map.boardToBoardResponse(board);
    }

    public BoardResponse updateBoard(ObjectId id, Board updatedBoard){
        boardDao.updateBoard(id, updatedBoard);
        Board board = boardDao.getBoardById(id).orElseThrow(BoardNotFoundException::new);
        return BoardMapper.map.boardToBoardResponse(board);
    }

    public void deleteBoard(ObjectId id){
        boardDao.deleteBoard(id);
    }
}
