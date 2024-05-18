package com.utcn.demo.controller;

import com.utcn.demo.model.Question;
import com.utcn.demo.model.User;
import com.utcn.demo.repository.dtos.questiondto.QuestionCreateDTO;
import com.utcn.demo.repository.dtos.questiondto.QuestionUpdateDTO;
import com.utcn.demo.service.QuestionService;
import com.utcn.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;
    //create a question
    @PostMapping("/create")
    public void createQuestion(@RequestBody QuestionCreateDTO questionCreateDTO) {
        User author = userService.retrieveUser(questionCreateDTO.getUser_id());
        questionService.createQuestion(author, questionCreateDTO);
    }
    //delete a question
    @DeleteMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }
    //update a question
    @PostMapping("/update")
    public void updateQuestion(@RequestBody QuestionUpdateDTO questionUpdateDTO) {
        questionService.updateQuestion(questionUpdateDTO);
    }

    //get question by id
    @GetMapping("/get/{id}")
    public Question retrieveQuestion(@PathVariable Long id) {
        return questionService.retrieveQuestion(id);
    }
    //get all questions
    @GetMapping("/getAll")
    public List<Question> retrieveQuestions() {
        return questionService.retrieveQuestions();
    }
    //delete all questions
    @DeleteMapping("/deleteAll")
    public void deleteAllQuestions() {
        questionService.deleteAllQuestions();
    }

}
