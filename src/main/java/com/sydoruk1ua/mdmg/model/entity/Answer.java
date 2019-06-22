package com.sydoruk1ua.mdmg.model.entity;

import java.util.Objects;

public class Answer {
    private final Integer id;
    private final Integer questionId;
    private final String answerEn;
    private final String answerRu;
    private final String isCorrect;

    private Answer(Builder builder) {
        this.id = builder.id;
        this.questionId = builder.questionId;
        this.answerEn = builder.answerEn;
        this.answerRu = builder.answerRu;
        this.isCorrect = builder.isCorrect;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public String getAnswerEn() {
        return answerEn;
    }

    public String getAnswerRu() {
        return answerRu;
    }

    public String getCorrect() {
        return isCorrect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id) &&
                Objects.equals(questionId, answer.questionId) &&
                Objects.equals(answerEn, answer.answerEn) &&
                Objects.equals(answerRu, answer.answerRu) &&
                Objects.equals(isCorrect, answer.isCorrect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionId, answerEn, answerRu, isCorrect);
    }

    public static class Builder {
        private Integer id;
        private Integer questionId;
        private String answerEn;
        private String answerRu;
        private String isCorrect;

        private Builder() {

        }

        public Answer build() {
            return new Answer(this);
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withQuestionId(Integer questionId) {
            this.questionId = questionId;
            return this;
        }

        public Builder withAnswerEn(String answerEn) {
            this.answerEn = answerEn;
            return this;
        }

        public Builder withAnswerRu(String answerRu) {
            this.answerRu = answerRu;
            return this;
        }

        public Builder withIsCorrect(String isCorrect) {
            this.isCorrect = isCorrect;
            return this;
        }
    }
}
