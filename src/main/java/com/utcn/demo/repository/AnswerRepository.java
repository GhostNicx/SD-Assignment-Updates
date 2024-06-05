package com.utcn.demo.repository;

import com.utcn.demo.model.Answer;
import com.utcn.demo.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestion(Question question);

    @Query("SELECT q FROM Answer q ORDER BY q.creationDate ASC")
    List<Answer> findAllOrderByCreationDateAsc();
}
