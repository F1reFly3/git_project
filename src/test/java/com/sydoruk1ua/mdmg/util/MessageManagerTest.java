package com.sydoruk1ua.mdmg.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageManagerTest {

    @Test
    public void shouldReturnLoginErrorMessage() {
        String expectedMessage = "Incorrect login or password";
        String actualMessage = MessageManager.getProperty("login.error.message");

        assertEquals(expectedMessage, actualMessage);
    }
}