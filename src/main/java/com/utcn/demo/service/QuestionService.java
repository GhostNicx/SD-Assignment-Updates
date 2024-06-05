package com.utcn.demo.service;

import com.utcn.demo.model.Question;
import com.utcn.demo.model.User;
import com.utcn.demo.repository.dtos.questiondto.QuestionCreateDTO;
import com.utcn.demo.repository.QuestionRepository;
import com.utcn.demo.repository.dtos.questiondto.QuestionDTO;
import com.utcn.demo.repository.dtos.questiondto.QuestionUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utcn.demo.repository.ResourceNotFoundException;

import java.util.Date;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserService userService;

    public void createQuestion(User author, QuestionCreateDTO questionCreateDTO) {
        Question question = new Question();
        question.setTitle(questionCreateDTO.getTitle());
        question.setQuestion(questionCreateDTO.getQuestion());
        question.setTag(questionCreateDTO.getTag());
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

    public void upvoteQuestion(long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException("Question not found"));
        question.upvote();
        questionRepository.save(question);
        userService.updateUserScore(question.getAuthor(), 2.5); // add points for question upvote
    }

    public void downvoteQuestion(long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException("Question not found"));
        question.downvote();
        questionRepository.save(question);
        userService.updateUserScore(question.getAuthor(), -1.5);
    }

    public Question retrieveQuestion(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    public List<Question> retrieveQuestions() {
        return (List<Question>) questionRepository.findAll();
    }

    public List<QuestionDTO> retrieveQuestionsSorted() {
        List<Question> questions = questionRepository.findAllOrderByCreationDateAsc();
        return questions.stream().map(this::convertToDTO).toList();
    }

    public List<Question> retrieveQuestionsByAuthor(User author) {
        return questionRepository.findByAuthor(author);
    }


    //delete a question by only if the user id is valid to that question
    public void deleteQuestionByAuthor(Long id, User author) {
        Question question = questionRepository.findById(id).orElse(null);
        if (author.getRole().name().equals("ADMIN") || (question != null && question.getAuthor().equals(author))) {
            questionRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Question with ID " + id + " not found");
        }
    }

    //update a question by only if the user id is valid to that question
    public void updateQuestionByAuthor(QuestionUpdateDTO questionUpdateDTO, User author) {
        Question question = questionRepository.findById(questionUpdateDTO.getQuestion_id()).orElse(null);
        if (author.getRole().name().equals("ADMIN") ) {
            question.setTitle(questionUpdateDTO.getTitle());
            question.setQuestion(questionUpdateDTO.getQuestion());
            questionRepository.save(question);
        }
        else if(question != null && question.getAuthor().equals(author)){
                question.setTitle(questionUpdateDTO.getTitle());
                question.setQuestion(questionUpdateDTO.getQuestion());
                questionRepository.save(question);
            }
        else {
            throw new IllegalArgumentException("illegal operation");
        }
    }

    //search by tag
    public List<QuestionDTO> retrieveQuestionsByTag(String tag) {
        List<Question> questions = questionRepository.findByTag(tag);
        return questions.stream().map(this::convertToDTO).toList();
    }

    private QuestionDTO convertToDTO(Question question) {
        return new QuestionDTO(
                question.getId(),
                question.getTitle(),
                question.getQuestion(),
                question.getTag(),
                question.getCreationDate(),
                question.getQuestionScore(),
                question.getAuthor().getUsername(),
                question.getAuthor().getCnp()
        );
    }
}
