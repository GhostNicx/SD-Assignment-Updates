package com.utcn.demo.repository;

import com.utcn.demo.model.Question;
import com.utcn.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByAuthor(User author);
    @Query("SELECT q FROM Question q ORDER BY q.creationDate ASC")
    List<Question> findAllOrderByCreationDateAsc();

    @Query("SELECT q FROM Question q where q.id = id")
    Question findByIdNew(Long id);

    List<Question> findByTag(String tag);
}
