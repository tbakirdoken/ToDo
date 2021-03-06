package com.springboot.todo.ToDoList.dao;

import com.springboot.todo.ToDoList.model.Board;
import com.springboot.todo.ToDoList.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDao {

    Item addItem(Item item);

    List<Item> getAllItemsByBoard(String boardID, int page, int pageSize, String sortBy);

    Optional<Item> getItemById(String id);

    void updateItem(String id, Item updatedItem);

    void deleteItem(String id);
}
