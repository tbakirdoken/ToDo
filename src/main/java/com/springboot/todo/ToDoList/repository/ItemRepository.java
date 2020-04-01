package com.springboot.todo.ToDoList.repository;

import com.springboot.todo.ToDoList.dao.ItemDao;
import com.springboot.todo.ToDoList.model.Board;
import com.springboot.todo.ToDoList.model.Item;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository("Items")
public class ItemRepository implements ItemDao {

    @NotNull
    final MongoTemplate mongoTemplate;

    @Override
    public Item addItem(Item item) {
       return mongoTemplate.save(item);
    }

    @Override
    public List<Item> getAllItems() {
        return mongoTemplate.findAll(Item.class);
    }

    @Override
    public Optional<Item> getItemById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(new ObjectId(id), Item.class));
    }

    @Override
    public void updateItem(String id, Item updatedItem) {
        updatedItem.setID(id);
        mongoTemplate.save(updatedItem);
    }

    @Override
    public void deleteItem(String id) {
        mongoTemplate.findAndRemove(Query.query(Criteria.where("ID").is(id)), Item.class);
    }
}
