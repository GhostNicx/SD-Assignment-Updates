package com.utcn.demo.repository.dtos.questiondto;

public class QuestionUpdateDTO {
    private long question_id;
    private String question;
    private String title;

    public QuestionUpdateDTO(long question_id, String question, String title) {
        this.question_id = question_id;
        this.question = question;
        this.title = title;
    }

    public QuestionUpdateDTO() {
    }

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
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
