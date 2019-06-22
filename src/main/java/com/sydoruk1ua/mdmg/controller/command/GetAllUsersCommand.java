package com.sydoruk1ua.mdmg.controller.command;

import com.sydoruk1ua.mdmg.model.service.UserService;
import com.sydoruk1ua.mdmg.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllUsersCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(GetAllUsersCommand.class);
    private final UserService userService;

    public GetAllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("usersList", userService.findAll());
        LOGGER.debug("Count of all users: " + userService.findAll().size());
        return ConfigurationManager.getProperty("users.list.page.path");
    }
}
