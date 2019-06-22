package com.sydoruk1ua.mdmg.model.service;

import com.sydoruk1ua.mdmg.model.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    Optional<Integer> create(Question question);

    List<Question> findAll();

    void update(Question question);
}
