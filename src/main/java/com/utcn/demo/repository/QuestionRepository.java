package com.utcn.demo.repository;

import com.utcn.demo.model.Question;
import com.utcn.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByAuthor(User author);
}