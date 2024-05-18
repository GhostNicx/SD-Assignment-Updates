package com.utcn.demo.repository.dtos.questiondto;


public class QuestionCreateDTO {
    private long user_id;
    private String question;
    private String title;

    public QuestionCreateDTO(long user_id, String question, String title) {
        this.user_id = user_id;
        this.question = question;
        this.title = title;
    }

    public QuestionCreateDTO() {
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
