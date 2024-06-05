package com.utcn.demo.repository.dtos.questiondto;

import java.time.LocalDateTime;
import java.util.Date;

public class QuestionDTO {
    private Long id;
    private String title;
    private String question;
    private String tag;
    private Date creationDate;
    private int questionScore;
    private String authorUsername;

    private long userId;

    // Constructor, getters, and setters
    public QuestionDTO(Long id, String title, String question, String tag, Date creationDate, int questionScore, String authorUsername, long userId) {
        this.id = id;
        this.title = title;
        this.question = question;
        this.tag = tag;
        this.creationDate = creationDate;
        this.questionScore = questionScore;
        this.authorUsername = authorUsername;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(int questionScore) {
        this.questionScore = questionScore;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
