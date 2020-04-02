package com.springboot.todo.ToDoList.controller;

import com.springboot.todo.ToDoList.model.Item;
import com.springboot.todo.ToDoList.service.ItemService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
@Api
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public Item addItem(@RequestBody Item item){
        return itemService.addItem(item);
    }

    @GetMapping
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping(path = "{id}")
    public Optional<Item> getBoardById(@PathVariable String id){
        return itemService.getItemById(id);
    }

    @PutMapping(path = "{id}")
    public Item updateItem(@PathVariable("id") String id, @RequestBody Item updatedItem){
        return itemService.updateItem(id, updatedItem);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") String id){
        itemService.deleteItem(id);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }
}
