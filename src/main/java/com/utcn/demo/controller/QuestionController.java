package com.utcn.demo.controller;

import com.utcn.demo.model.Question;
import com.utcn.demo.model.User;
import com.utcn.demo.repository.dtos.questiondto.QuestionCreateDTO;
import com.utcn.demo.repository.dtos.questiondto.QuestionDTO;
import com.utcn.demo.repository.dtos.questiondto.QuestionUpdateDTO;
import com.utcn.demo.service.QuestionService;
import com.utcn.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "http://localhost:4200")
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
    public List<QuestionDTO> retrieveQuestionsSorted() {
        return questionService.retrieveQuestionsSorted();
    }

    //delete all questions
    @DeleteMapping("/deleteAll")
    public void deleteAllQuestions() {
        questionService.deleteAllQuestions();
    }

    //upvote a question
    @PostMapping("/upvote/{id}")
    public void upvoteQuestion(@PathVariable Long id) {
        questionService.upvoteQuestion(id);
    }
    //downvote a question
    @PostMapping("/downvote/{id}")
    public void downvoteQuestion(@PathVariable Long id) {
        questionService.downvoteQuestion(id);
    }

    //delete question by author
    @DeleteMapping("/deleteByAuthor/{id}")
    public void deleteQuestionByAuthor(@PathVariable Long id, @RequestParam Long userId) {
        User author = userService.retrieveUser(userId);
        questionService.deleteQuestionByAuthor(id, author);
    }

    @PostMapping("/updateByAuthor/{userId}")
    public void updateQuestionByAuthor(@RequestBody QuestionUpdateDTO questionUpdateDTO, @PathVariable Long userId) {
        User author = userService.retrieveUser(userId);
        questionService.updateQuestionByAuthor(questionUpdateDTO, author);
    }

    //find questions by tag
    @GetMapping("/findByTag/{tag}")
    public List<QuestionDTO> retrieveQuestionsByTag(@PathVariable String tag) {
        return questionService.retrieveQuestionsByTag(tag);
    }

}
