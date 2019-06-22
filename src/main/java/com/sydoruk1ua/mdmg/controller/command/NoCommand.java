package com.sydoruk1ua.mdmg.controller.command;

import com.sydoruk1ua.mdmg.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCommand implements Command {
    /**
     * in case of direct access to the controller, will redirect to the login page
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return ConfigurationManager.getProperty("main.page.path");
    }
}
