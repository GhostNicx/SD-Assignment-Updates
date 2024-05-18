package com.utcn.demo.service;

import com.utcn.demo.model.Question;
import com.utcn.demo.model.User;
import com.utcn.demo.repository.dtos.questiondto.QuestionCreateDTO;
import com.utcn.demo.repository.QuestionRepository;
import com.utcn.demo.repository.dtos.questiondto.QuestionUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public void createQuestion(User author, QuestionCreateDTO questionCreateDTO) {
        Question question = new Question();
        question.setTitle(questionCreateDTO.getTitle());
        question.setQuestion(questionCreateDTO.getQuestion());
        question.setCreationDate(new Date());
        question.setAuthor(author);
        questionRepository.save(question);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
    //delete all questions
    public void deleteAllQuestions() {
        questionRepository.deleteAll();
    }
    //updating question using questionUpdateDTO
    public void updateQuestion(QuestionUpdateDTO questionUpdateDTO) {
        Question question = questionRepository.findById(questionUpdateDTO.getQuestion_id()).orElse(null);
        if (question != null) {
            question.setTitle(questionUpdateDTO.getTitle());
            question.setQuestion(questionUpdateDTO.getQuestion());
            questionRepository.save(question);
        }else {
            throw new IllegalArgumentException("Question with ID " + questionUpdateDTO.getQuestion_id() + " not found");
        }

    }
    public Question retrieveQuestion(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    public List<Question> retrieveQuestions() {
        return (List<Question>) questionRepository.findAll();
    }

    public List<Question> retrieveQuestionsByAuthor(User author) {
        return questionRepository.findByAuthor(author);
    }
}
