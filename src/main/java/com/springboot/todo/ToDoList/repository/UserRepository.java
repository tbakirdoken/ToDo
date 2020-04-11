package com.springboot.todo.ToDoList.repository;

import com.springboot.todo.ToDoList.dao.UserDao;
import com.springboot.todo.ToDoList.model.User;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository("Users")
public class UserRepository implements UserDao {

    @NotNull
    final MongoTemplate mongoTemplate;

    @Override
    public User addUser(User user) {
        return mongoTemplate.save(user);
    }

    @Override
    public List<User> getAllUsers(int page, int size, String sortBy) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return mongoTemplate.find( new Query().with(pageable), User.class);
    }

    @Override
    public Optional<User> getUserById(ObjectId id) {
        return Optional.ofNullable(mongoTemplate.findById(id, User.class));
    }

    @Override
    public void updateUser(ObjectId id, User updatedUser) {
        updatedUser.setId(id);
        updatedUser.setUpdateDate(new Date());
        mongoTemplate.save(updatedUser);
    }

    @Override
    public void deleteUser(ObjectId id) {
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("id").is(id)),
                new Update()
                        .set("isActive", "0"), User.class);
         // mongoTemplate.findAndRemove(Query.query(Criteria.where("ID").is(id)), User.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(mongoTemplate.findOne(Query.query(Criteria.where("email").is(email)), User.class));

    }
}
