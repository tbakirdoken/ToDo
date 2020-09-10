package com.springboot.todo.ToDoList.repository;

import com.springboot.todo.ToDoList.dao.BoardDao;
import com.springboot.todo.ToDoList.model.Board;
import com.springboot.todo.ToDoList.model.User;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
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
@Repository("Boards")
public class BoardRepository implements BoardDao {

    @NotNull
    final MongoTemplate mongoTemplate;

    @Override
    public Board addBoard(Board board) {
        return mongoTemplate.save(board);
    }

    @Override
    public List<Board> getAllBoardsByUser(String userID, int page, int pageSize, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(sortBy));
        return mongoTemplate.find(
                new Query()
                        .addCriteria(Criteria.where("userID").is(userID))
                        .with(pageRequest),
                Board.class);
    }

    @Override
    public Optional<Board> getBoardById(ObjectId id) {
        return Optional.ofNullable(mongoTemplate.findById(id, Board.class));
    }

    @Override
    public void updateBoard(ObjectId id, Board updatedBoard) {
        mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(id)), new Update().set("name", updatedBoard.getName())
                .set("updateDate", new Date()), Board.class);
    }

    @Override
    public void deleteBoard(ObjectId id) {
        mongoTemplate.findAndRemove(Query.query(Criteria.where("id").is(id)), Board.class);
    }
}
