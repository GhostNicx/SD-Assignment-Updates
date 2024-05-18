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
    private long id;

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

    public Question() {
    }

    public Question(long id, User author, String title, String question, Date creationDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.question = question;
        this.creationDate = creationDate;
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
}
