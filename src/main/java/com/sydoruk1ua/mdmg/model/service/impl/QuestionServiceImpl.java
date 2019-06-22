package com.sydoruk1ua.mdmg.model.service.impl;

import com.sydoruk1ua.mdmg.model.dao.QuestionDao;
import com.sydoruk1ua.mdmg.model.entity.Question;
import com.sydoruk1ua.mdmg.model.entity.QuestionType;
import com.sydoruk1ua.mdmg.model.service.QuestionService;

import java.util.List;
import java.util.Optional;

public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public Optional<Integer> create(Question question) {
        Question questionToDb = Question.builder()
                .withQuestionEn(question.getQuestionEn())
                .withQuestionRu(question.getQuestionRu())
                .withPromptEn(question.getPromptEn())
                .withPromptRu(question.getPromptRu())
                .withQuestionType(getQuestionTypeWithIdByType(question.getQuestionType().getType()))
                .build();
        return questionDao.create(questionToDb);
    }

    private QuestionType getQuestionTypeWithIdByType(String type) {
        if ("multi".equals(type)) {
            return new QuestionType(2, "multi");
        }
        return new QuestionType(1, "single");
    }

    @Override
    public List<Question> findAll() {
        return questionDao.findAll();
    }

    @Override
    public void update(Question question) {
        throw new UnsupportedOperationException();
    }
}
