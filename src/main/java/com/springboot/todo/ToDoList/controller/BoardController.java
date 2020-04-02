package com.springboot.todo.ToDoList.controller;

import com.springboot.todo.ToDoList.model.Board;
import com.springboot.todo.ToDoList.model.Item;
import com.springboot.todo.ToDoList.service.BoardService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/boards")
@Api
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping
    public Board addBoard(@RequestBody Board board){
        return boardService.addBoard(board);
    }

    @GetMapping
    public List<Board> getAllBoardsByUser(@RequestParam String userID, @RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "10", required = false) int pageSize, @RequestParam(defaultValue = "updatedAt", required = false) String sortBy ){
        return boardService.getAllBoardsByUser(userID, page, pageSize, sortBy);
    }

    @GetMapping(path = "{id}")
    public Optional<Board> getBoardById(@PathVariable String id){
        return boardService.getBoardById(id);
    }

    @PutMapping(path = "{id}")
    public Board updateBoard(@PathVariable("id") String id, @RequestBody Board updatedBoard){
         return boardService.updateBoard(id, updatedBoard);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable("id") String id){
        boardService.deleteBoard(id);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }
}
