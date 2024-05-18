package com.utcn.demo.repository.dtos.answerdto;

public class AnswerCreateDTO {
private long author_id;
    private long question_id;
    private String answer;

    public AnswerCreateDTO(long author_id, long question_id, String answer) {
        this.author_id = author_id;
        this.question_id = question_id;
        this.answer = answer;
    }

    public AnswerCreateDTO() {
    }

    public long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
