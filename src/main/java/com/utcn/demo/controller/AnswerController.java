package com.utcn.demo.controller;

import com.utcn.demo.model.Answer;
import com.utcn.demo.model.Question;
import com.utcn.demo.model.User;
import com.utcn.demo.repository.dtos.answerdto.AnswerCreateDTO;
import com.utcn.demo.repository.dtos.answerdto.AnswerUpdateDTO;
import com.utcn.demo.service.AnswerService;
import com.utcn.demo.service.QuestionService;
import com.utcn.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public void createAnswer(@RequestBody AnswerCreateDTO answerCreateDTO) {
        User author = userService.retrieveUser(answerCreateDTO.getAuthor_id());
        Question question = questionService.retrieveQuestion(answerCreateDTO.getQuestion_id());
        answerService.createAnswer(author, question,answerCreateDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllAnswers() {
        answerService.deleteAllAnswers();
    }

    @PostMapping("/update")
    public void updateAnswer(@RequestBody AnswerUpdateDTO answerUpdateDTO) {
        answerService.updateAnswer(answerUpdateDTO);
    }

    @GetMapping("/get/{id}")
    public Answer retrieveAnswer(@PathVariable Long id) {
        return answerService.retrieveAnswer(id);
    }

    @GetMapping("/getAll")
    public List<Answer> retrieveAnswers() {
        return answerService.retrieveAnswers();
    }

    @GetMapping("/getByQuestion/{id}")
    public List<Answer> retrieveAnswersByQuestion(@PathVariable Long id) {
        Question question = questionService.retrieveQuestion(id);
        return answerService.retrieveAnswersByQuestion(question);
    }

}
