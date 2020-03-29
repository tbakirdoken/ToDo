package com.springboot.todo.ToDoList.repository;

import com.mongodb.Mongo;
import com.mongodb.client.result.UpdateResult;
import com.springboot.todo.ToDoList.dao.UserDao;
import com.springboot.todo.ToDoList.model.User;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository("Users")
public class UserRepository implements UserDao {
    //Veritabanı işlemleri gelecek
    @NotNull
    final MongoTemplate mongoTemplate;

    @Override
    public User addUser(User user) {
        return mongoTemplate.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(new ObjectId(id), User.class));
    }

    @Override
    public void updateUser(String id, User updatedUser) {
        updatedUser.setID(id);
        mongoTemplate.save(updatedUser);
//        mongoTemplate.updateFirst(
//                Query.query(Criteria.where("ID").is(id)),
//                new Update()
//                        .set("firstName", updatedUser.getFirstName())
//                        .set("lastName", updatedUser.getLastName())
//                        .set("email", updatedUser.getEmail())
//                        .set("password", updatedUser.getPassword())
//                        .set("isActive", updatedUser.isActive())
//                        .set("lastLogin", updatedUser.getLastLogin())
//                        .set("dateJoined", updatedUser.getDateJoined()), User.class);

    }

    @Override
    public void deleteUser(String id) {
         mongoTemplate.findAndRemove(Query.query(Criteria.where("ID").is(id)), User.class);
    }
}
