package com.sydoruk1ua.mdmg.util;

import org.apache.commons.codec.digest.DigestUtils;

public final class PasswordEncryptor {
    private PasswordEncryptor() {
    }

    public static String encrypt(String field) {
        return DigestUtils.md2Hex(field);
    }
}
