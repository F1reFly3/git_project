package com.sydoruk1ua.mdmg.util;

import java.util.ResourceBundle;

/**
 * The {@code ConfigurationManager} class used to obtain the properties
 * by key from {@code "config.properties"} file which has information
 * about the connection to a database and to page paths
 */
public final class ConfigurationManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

    private ConfigurationManager() {
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
