package com.sydoruk1ua.mdmg.controller.command;

import com.sydoruk1ua.mdmg.model.entity.User;
import com.sydoruk1ua.mdmg.model.service.UserService;
import com.sydoruk1ua.mdmg.util.ConfigurationManager;
import com.sydoruk1ua.mdmg.util.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PASSWORD = "password";
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter(PARAM_NAME_EMAIL);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        Optional<User> optionalUser = userService.findByEmailAndPassword(email, pass);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            request.getSession().setAttribute("user", user.getEmail());
            request.getSession().setAttribute("userRole", user.getRole().getType());
            return ConfigurationManager.getProperty("main.page.path");
        } else {
            request.setAttribute("loginError", MessageManager.getProperty("login.error.message"));
            return ConfigurationManager.getProperty("login.page.path");
        }
    }
}
