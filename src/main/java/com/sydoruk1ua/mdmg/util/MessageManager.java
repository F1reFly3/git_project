package com.sydoruk1ua.mdmg.util;

import java.util.ResourceBundle;

public final class MessageManager {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    private MessageManager() {
    }

    /**
     * Method which is used to get value of the property obtained by the key
     *
     * @param key {@code key} used to find properties
     * @return returns the value of the property obtained by the key
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
