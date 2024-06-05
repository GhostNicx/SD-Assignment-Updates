package com.utcn.demo.repository.dtos.questiondto;


public class QuestionCreateDTO {
    private long user_id;
    private String question;
    private String title;

    private String tag;

    public QuestionCreateDTO(long user_id, String question, String title, String tag) {
        this.user_id = user_id;
        this.question = question;
        this.title = title;
        this.tag = tag;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
