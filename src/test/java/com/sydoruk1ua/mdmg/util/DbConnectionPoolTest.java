package com.sydoruk1ua.mdmg.util;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class DbConnectionPoolTest {

    @Test
    public void shouldInstallConnection() {

        for (int i = 0; i < 50; i++) {
            try {
                assertTrue(DbConnectionPoolUtil.getConnection().isValid(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}