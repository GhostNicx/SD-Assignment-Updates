package com.utcn.demo.repository.dtos.answerdto;

public class AnswerUpdateDTO {
    private long answer_id;
    private String answer;

    public AnswerUpdateDTO(long answer_id, String answer) {
        this.answer_id = answer_id;
        this.answer = answer;
    }

    public AnswerUpdateDTO() {
    }

    public long getAnswerId() {
        return answer_id;
    }

    public void setId(long answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
