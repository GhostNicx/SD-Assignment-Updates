package com.utcn.demo.service;

import com.utcn.demo.model.Answer;
import com.utcn.demo.model.Question;
import com.utcn.demo.model.User;
import com.utcn.demo.repository.*;
import com.utcn.demo.repository.dtos.answerdto.AnswerCreateDTO;
import com.utcn.demo.repository.dtos.answerdto.AnswerDTO;
import com.utcn.demo.repository.dtos.answerdto.AnswerUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserService userService;


    public void createAnswer(User author, Question question, AnswerCreateDTO answerCreateDTO) {
        Answer answer = new Answer();
        Question currentQuestion = questionRepository.getReferenceById(answerCreateDTO.getQuestion_id());
        answer.setAuthor(author);
        answer.setQuestion(currentQuestion);
        answer.setAnswer(answerCreateDTO.getAnswer());
        answer.setCreationDate(new Date());
        answerRepository.save(answer);
    }

    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }

    public void deleteAllAnswers() {
        answerRepository.deleteAll();
    }

    public void updateAnswer(AnswerUpdateDTO answerUpdateDTO) {
        Answer answer = answerRepository.findById(answerUpdateDTO.getAnswerId()).orElse(null);
        if (answer != null) {
            answer.setAnswer(answerUpdateDTO.getAnswer());
            answerRepository.save(answer);
        }else {
            throw new IllegalArgumentException("Answer with ID " + answerUpdateDTO.getAnswerId() + " not found");
        }
    }

    public Answer retrieveAnswer(Long id) {
        return answerRepository.findById(id).orElse(null);
    }

    public List<Answer> retrieveAnswers() {
        return answerRepository.findAll();
    }
    //retreive all answers sorted ascendingingly
    public List<AnswerDTO> retrieveAnswersSorted() {
        List<Answer> answers = answerRepository.findAllOrderByCreationDateAsc();
        return answers.stream().map(this::convertToDTO).toList();
    }
    //retreive all answers to a question
    public List<Answer> retrieveAnswersByQuestion(Question question) {
        return answerRepository.findByQuestion(question);
    }


    public void upvoteAnswer(long answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new ResourceNotFoundException("Answer not found"));
        answer.upvote();
        answerRepository.save(answer);
        userService.updateUserScore(answer.getAuthor(), 5.0); // add points for answer upvote
    }

    public void downvoteAnswer(long answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new ResourceNotFoundException("Answer not found"));
        answer.downvote();
        answerRepository.save(answer);
        userService.updateUserScore(answer.getAuthor(), -2.5); // subtract points for answer downvote
    }

    private AnswerDTO convertToDTO(Answer answer) {
        return new AnswerDTO(
                answer.getId(),
                answer.getAnswer(),
                answer.getCreationDate(),
                answer.getAuthor().getUsername(),
                answer.getAuthor().getCnp(),
                answer.getQuestion().getId(),
                answer.getAnswerScore()
        );
    }
}
