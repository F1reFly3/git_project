package com.sydoruk1ua.mdmg.model.dao.impl;

import com.sydoruk1ua.mdmg.model.dao.MessageDao;
import com.sydoruk1ua.mdmg.model.entity.Message;
import com.sydoruk1ua.mdmg.util.DbConnectionPoolUtil;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageDaoImpl implements MessageDao {
    private static final Logger LOGGER = Logger.getLogger(MessageDaoImpl.class);

    private static final String INSERT_MESSAGES = "INSERT INTO messages(user_email, message) " +
            "VALUES(?, ?)";
    private static final String FIND_ALL_MESSAGES = "SELECT * FROM messages";

    @Override
    public Optional<Integer> create(Message message) {
        try (PreparedStatement statement = DbConnectionPoolUtil.getConnection().prepareStatement(INSERT_MESSAGES,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, message.getUserEmail());
            statement.setString(2, message.getValue());

            return DbConnectionPoolUtil.getId(statement);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Message> findById(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Message> findAll() {
        List<Message> messageList = new ArrayList<>();
        try (PreparedStatement statement = DbConnectionPoolUtil.getConnection().prepareStatement(FIND_ALL_MESSAGES);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                do {
                    String userEmail = resultSet.getString("user_email");
                    String value = resultSet.getString("message");
                    messageList.add(new Message(userEmail, value));
                } while (resultSet.next());
            } else {
                LOGGER.warn("Result Set is empty!");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return messageList;
    }

    @Override
    public void update(Message entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException();
    }
}
