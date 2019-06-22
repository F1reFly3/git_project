package com.sydoruk1ua.mdmg.controller.command;

import com.sydoruk1ua.mdmg.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute("user");
            session.removeAttribute("userRole");
            session.invalidate();
        }
        return ConfigurationManager.getProperty("main.page.path");
    }
}
