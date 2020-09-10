package com.springboot.todo.ToDoList.controller;

import com.springboot.todo.ToDoList.dto.Response.BoardResponse;
import com.springboot.todo.ToDoList.model.Board;
import com.springboot.todo.ToDoList.model.Item;
import com.springboot.todo.ToDoList.service.BoardService;
import com.springboot.todo.ToDoList.util.JwtUtil;
import com.springboot.todo.ToDoList.util.ResponseUtil;
import com.springboot.todo.ToDoList.viewmodel.MainResponse;
import io.swagger.annotations.Api;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/boards")
@Api
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping
    public ResponseEntity<MainResponse<BoardResponse>> addBoard(@RequestBody Board board, HttpServletRequest httpServletRequest){
        String id = JwtUtil.getObjectIdFromRequest(httpServletRequest);
        board.setUserID(id);
        return ResponseUtil.data(boardService.addBoard(board), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<MainResponse<List<BoardResponse>>> getAllBoardsByUser(@RequestParam String userID, @RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "10", required = false) int pageSize, @RequestParam(defaultValue = "updatedAt", required = false) String sortBy ){
        return ResponseUtil.data(boardService.getAllBoardsByUser(userID, page, pageSize, sortBy));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<MainResponse<BoardResponse>> getBoardById(@PathVariable ObjectId id){
        return ResponseUtil.data(boardService.getBoardById(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<MainResponse<BoardResponse>> updateBoard(@PathVariable("id") ObjectId id, @RequestBody Board updatedBoard){
         return ResponseUtil.data(boardService.updateBoard(id, updatedBoard));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<MainResponse<String>> deleteBoard(@PathVariable("id") ObjectId id){
        boardService.deleteBoard(id);
        return ResponseUtil.data("Deleted", HttpStatus.NO_CONTENT);
    }
}
