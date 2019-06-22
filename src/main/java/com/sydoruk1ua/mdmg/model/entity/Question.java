package com.sydoruk1ua.mdmg.model.entity;

import java.util.Objects;

public class Question {
    private final Integer id;
    private final QuestionType questionType;
    private final String questionEn;
    private final String questionRu;
    private final String promptEn;
    private final String promptRu;

    private Question(Builder builder) {
        this.id = builder.id;
        this.questionType = builder.questionType;
        this.questionEn = builder.questionEn;
        this.questionRu = builder.questionRu;
        this.promptEn = builder.promptEn;
        this.promptRu = builder.promptRu;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Question question = (Question) o;
        return Objects.equals(id, question.id) &&
                Objects.equals(questionType, question.questionType) &&
                Objects.equals(questionEn, question.questionEn) &&
                Objects.equals(questionRu, question.questionRu) &&
                Objects.equals(promptEn, question.promptEn) &&
                Objects.equals(promptRu, question.promptRu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionType, questionEn, questionRu, promptEn, promptRu);
    }

    public Integer getId() {
        return id;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public String getQuestionEn() {
        return questionEn;
    }

    public String getQuestionRu() {
        return questionRu;
    }

    public String getPromptEn() {
        return promptEn;
    }

    public String getPromptRu() {
        return promptRu;
    }

    public static class Builder {
        private Integer id;
        private QuestionType questionType;
        private String questionEn;
        private String questionRu;
        private String promptEn;
        private String promptRu;

        private Builder() {

        }

        public Question build() {
            return new Question(this);
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withQuestionType(QuestionType questionType) {
            this.questionType = questionType;
            return this;
        }

        public Builder withQuestionEn(String questionEn) {
            this.questionEn = questionEn;
            return this;
        }

        public Builder withQuestionRu(String questionRu) {
            this.questionRu = questionRu;
            return this;
        }

        public Builder withPromptEn(String promptEn) {
            this.promptEn = promptEn;
            return this;
        }

        public Builder withPromptRu(String promptRu) {
            this.promptRu = promptRu;
            return this;
        }
    }
}
