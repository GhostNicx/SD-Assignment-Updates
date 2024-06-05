package com.utcn.demo.repository.dtos.answerdto;
import java.util.Date;

public class AnswerDTO {
    private long id;
    private String answer;
    private Date creationDate;
    private String authorUsername;
    private long userId;
    private long questionId;
    private int answerScore;

    // Constructor, getters, and setters
    public AnswerDTO(long id, String answer, Date creationDate, String authorUsername, long userId, long questionId, int answerScore) {
        this.id = id;
        this.answer = answer;
        this.creationDate = creationDate;
        this.authorUsername = authorUsername;
        this.userId = userId;
        this.questionId = questionId;
        this.answerScore = answerScore;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public int getAnswerScore() {
        return answerScore;
    }

    public void setAnswerScore(int answerScore) {
        this.answerScore = answerScore;
    }
}
