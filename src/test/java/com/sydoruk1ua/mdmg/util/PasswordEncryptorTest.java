package com.sydoruk1ua.mdmg.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PasswordEncryptorTest {

    @Test
    public void shouldEncryptPassword() {
        String password = "superStrongPassword";
        String passwordEncrypted = "57bb9bc48390ceaa1f0d0e574e18bdce";
        assertEquals(passwordEncrypted, PasswordEncryptor.encrypt(password));
    }
}