package com.springboot.todo.ToDoList.service;

import com.springboot.todo.ToDoList.dao.ItemDao;
import com.springboot.todo.ToDoList.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemDao itemDao;

    @Autowired
    public ItemService(@Qualifier("Items") ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public Item addItem(Item item){
        return itemDao.addItem(item);
    }

    public List<Item> getAllItems(){
        return itemDao.getAllItems();
    }

    public Optional<Item> getItemById(String id){
        return itemDao.getItemById(id);
    }

    public Item updateItem(String id, Item updatedItem){
        itemDao.updateItem(id,updatedItem);
        Optional<Item> itemOp = itemDao.getItemById(id);
        Item item = itemOp.get();
        return item;
    }

    public void deleteItem(String id){
        itemDao.deleteItem(id);
    }
}
