package com.sydoruk1ua.mdmg.model.dao.impl;

import com.sydoruk1ua.mdmg.model.dao.QuestionDao;
import com.sydoruk1ua.mdmg.model.entity.Question;
import com.sydoruk1ua.mdmg.model.entity.QuestionType;
import com.sydoruk1ua.mdmg.util.DbConnectionPoolUtil;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestionDaoImpl implements QuestionDao {
    public static final String INSERT_QUESTION = "INSERT INTO questions (type_id, question_en, question_ru, " +
            "prompt_en, prompt_ru) VALUES (?, ?, ?, ?, ?)";
    private static final Logger LOGGER = Logger.getLogger(QuestionDaoImpl.class);
    private static final String FIND_ALL_QUESTIONS = "SELECT q.id, q.type_id, q.question_en, q.question_ru," +
            " q.prompt_en, q.prompt_ru, qt.type AS type_name " +
            "FROM questions AS q JOIN question_types AS qt ON q.type_id = qt.id;";


    @Override
    public Optional<Integer> create(Question question) {
        try (PreparedStatement statement = DbConnectionPoolUtil.getConnection().prepareStatement(INSERT_QUESTION,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, question.getQuestionType().getId());
            statement.setString(2, question.getQuestionEn());
            statement.setString(3, question.getQuestionRu());
            statement.setString(4, question.getPromptEn());
            statement.setString(5, question.getPromptRu());

            return DbConnectionPoolUtil.getId(statement);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Question> findById(java.lang.Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Question> findAll() {
        List<Question> listOfAllQuestions = new ArrayList<>();
        try (PreparedStatement statement = DbConnectionPoolUtil.getConnection().prepareStatement(FIND_ALL_QUESTIONS);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                do {
                    listOfAllQuestions.add(getQuestion(resultSet));
                } while (resultSet.next());
            } else {
                LOGGER.warn("Result Set is empty!");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return listOfAllQuestions;
    }

    private Question getQuestion(ResultSet resultSet) throws SQLException {
        Integer questionTypeId = resultSet.getInt("type_id");
        String questionTypeName = resultSet.getString("type_name");
        QuestionType questionType = new QuestionType(questionTypeId, questionTypeName);
        return Question.builder()
                .withId(resultSet.getInt("id"))
                .withQuestionType(questionType)
                .withQuestionEn(resultSet.getString("question_en"))
                .withQuestionRu(resultSet.getString("question_ru"))
                .withPromptEn(resultSet.getString("prompt_en"))
                .withPromptRu(resultSet.getString("prompt_ru"))
                .build();
    }

    @Override
    public void update(Question question) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(java.lang.Integer id) {
        throw new UnsupportedOperationException();
    }
}
