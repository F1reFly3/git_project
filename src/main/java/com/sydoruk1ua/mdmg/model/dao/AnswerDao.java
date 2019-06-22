package com.sydoruk1ua.mdmg.model.dao;

import com.sydoruk1ua.mdmg.model.entity.Answer;

import java.util.List;

public interface AnswerDao extends GenericDao<Answer> {
    boolean create(List<Answer> answers);
}
