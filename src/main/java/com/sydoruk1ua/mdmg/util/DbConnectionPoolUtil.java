package com.sydoruk1ua.mdmg.util;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public final class DbConnectionPoolUtil {
    private static final Logger LOGGER = Logger.getLogger(DbConnectionPoolUtil.class);
    private static BasicDataSource dataSource;

    private DbConnectionPoolUtil() {
    }

    public static Connection getConnection() throws SQLException {
        configConnection();
        LOGGER.debug("entered");
        return dataSource.getConnection();
    }

    private static void configConnection() {
        LOGGER.debug("entered");
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(ConfigurationManager.getProperty("db.driver"));
        ds.setUrl(ConfigurationManager.getProperty("db.url"));
        ds.setUsername(ConfigurationManager.getProperty("db.username"));
        ds.setPassword(ConfigurationManager.getProperty("db.password"));
        ds.setMinIdle(Integer.parseInt(ConfigurationManager.getProperty("db.min.idle")));
        ds.setMaxIdle(Integer.parseInt(ConfigurationManager.getProperty("db.max.idle")));
        ds.setMaxOpenPreparedStatements(Integer.parseInt(ConfigurationManager.getProperty(
                "db.max.open.prepare.statement")));
        dataSource = ds;
        LOGGER.debug("exit");
    }

    public static Optional<Integer> getId(PreparedStatement statement) throws SQLException {
        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            return Optional.empty();
        }
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return Optional.of(generatedKeys.getInt(1));
            } else {
                return Optional.empty();
            }
        }
    }
}