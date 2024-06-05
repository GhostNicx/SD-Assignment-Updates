package com.utcn.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "answer")
    private String answer;

    @Column(name = "creation_date")
    private Date creationDate;

    //answer score
    @Column(name = "answer_score")
    private int answerScore = 0;

    public Answer() {
    }

    public Answer(long id, User author, Question question, String answer, Date creationDate, int answerScore) {
        this.id = id;
        this.author = author;
        this.question = question;
        this.answer = answer;
        this.creationDate = creationDate;
        this.answerScore = answerScore;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
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

    public int getAnswerScore() {
        return answerScore;
    }

    public void setAnswerScore(int answerScore) {
        this.answerScore = answerScore;
    }

    public void upvote() {
        this.answerScore++;
        author.updateScore(5.0); // add points for upvote
    }

    // downvote method
    public void downvote() {
        this.answerScore--;
        author.updateScore(-2.5); // subtract points for downvote
    }

}
