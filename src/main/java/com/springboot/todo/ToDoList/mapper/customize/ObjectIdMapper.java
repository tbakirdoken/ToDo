package com.springboot.todo.ToDoList.mapper.customize;

import org.bson.types.ObjectId;

public class ObjectIdMapper {

    public String asString(ObjectId id) {

        return id == null ? "" : id.toString();

    }

    public ObjectId asObjectId(String id) {
        return new ObjectId(id);
    }
}
