package com.sydoruk1ua.mdmg.model.service.impl;

import com.sydoruk1ua.mdmg.model.dao.AnswerDao;
import com.sydoruk1ua.mdmg.model.entity.Answer;
import com.sydoruk1ua.mdmg.model.service.AnswerService;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {
    private final AnswerDao answerDao;

    public AnswerServiceImpl(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }

    @Override
    public boolean create(List<Answer> answers) {
        if (answers.size() == 1) {
            return answerDao.create(answers.get(0)).isPresent();
        } else {
            return answerDao.create(answers);
        }
    }
}
