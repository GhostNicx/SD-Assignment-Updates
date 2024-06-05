package com.utcn.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private User author;

    @Column(name = "title")
    private String title;

    @Column(name = "question")
    private String question;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "question_score")
    private int questionScore = 0;

    @Column(name = "tag")
    private String tag;

    public Question() {
    }

    public Question(long id, User author, String title, String question, Date creationDate, int questionScore, String tag) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.question = question;
        this.creationDate = creationDate;
        this.questionScore = questionScore;
        this.tag = tag;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void upvote() {
        this.questionScore++;
        author.updateScore(2.5); // add points for upvote
    }

    // downvote method
    public void downvote() {
        this.questionScore--;
        author.updateScore(-1.5); // subtract points for downvote
    }
}
