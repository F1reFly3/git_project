package com.sydoruk1ua.mdmg.model.dao.impl;

import com.sydoruk1ua.mdmg.model.dao.AnswerDao;
import com.sydoruk1ua.mdmg.model.entity.Answer;
import com.sydoruk1ua.mdmg.util.DbConnectionPoolUtil;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class AnswerDaoImpl implements AnswerDao {
    private static final Logger LOGGER = Logger.getLogger(AnswerDaoImpl.class);

    private static final String INSERT_ANSWER = "INSERT INTO answers (question_id, answer_en, answer_ru, is_correct)" +
            " VALUES (?, ?, ?, ?)";

    @Override
    public boolean create(List<Answer> answers) {
        try (PreparedStatement statement = DbConnectionPoolUtil.getConnection().prepareStatement(INSERT_ANSWER)) {
            for (Answer answer : answers) {
                statement.setInt(1, answer.getQuestionId());
                statement.setString(2, answer.getAnswerEn());
                statement.setString(3, answer.getAnswerRu());
                statement.setString(4, answer.getCorrect());
                statement.addBatch();
            }

            int[] affectedRows = statement.executeBatch();
            LOGGER.debug("affectedRows should be 4 but real count = " + affectedRows.length);

            return affectedRows.length == 4;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public Optional<Integer> create(Answer answer) {
        try (PreparedStatement statement = DbConnectionPoolUtil.getConnection().prepareStatement(INSERT_ANSWER,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, answer.getQuestionId());
            statement.setString(2, answer.getAnswerEn());
            statement.setString(3, answer.getAnswerRu());
            statement.setString(4, answer.getCorrect());

            return DbConnectionPoolUtil.getId(statement);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Answer> findById(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Answer> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Answer entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException();
    }
}
