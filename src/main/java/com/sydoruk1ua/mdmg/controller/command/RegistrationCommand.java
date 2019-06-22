package com.sydoruk1ua.mdmg.controller.command;

import com.sydoruk1ua.mdmg.model.entity.Role;
import com.sydoruk1ua.mdmg.model.entity.User;
import com.sydoruk1ua.mdmg.model.service.UserService;
import com.sydoruk1ua.mdmg.util.AttributeManager;
import com.sydoruk1ua.mdmg.util.ConfigurationManager;
import com.sydoruk1ua.mdmg.util.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.sydoruk1ua.mdmg.util.Validator.*;

public class RegistrationCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);
    private static final String REGISTRATION_PAGE_PATH = "registration.page.path";
    //parameters
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String PASSWORD_REPEAT = "password_repeat";
    //attributes
    private static final String INVALID_REGISTRATION_DATA = AttributeManager.getProperty("invalid.registration.data");
    private final UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Optional<User> optionalUser = getValidOptionalUser(request);
        if (!optionalUser.isPresent()) {
            LOGGER.debug("Registration failed because of invalid data");
            return ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH);
        }

        if (userService.findByEmail(request.getParameter(EMAIL)).isPresent()) {
            LOGGER.debug(MessageManager.getProperty("error.user.email.already.exist"));
            request.setAttribute("user_exist_error",
                    MessageManager.getProperty("error.user.email.already.exist"));
            return ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH);
        }
        String email = request.getParameter(EMAIL);
        if (userService.create(optionalUser.get())) {
            LOGGER.debug("User " + email + " successfully registered");
            request.getSession().setAttribute("user", email);
            request.getSession().setAttribute("userRole", "user");

            return ConfigurationManager.getProperty("main.page.path");
        }
        request.setAttribute("user_not_registered",
                MessageManager.getProperty("error.adding.user"));
        return ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH);
    }

    private Optional<User> getValidOptionalUser(HttpServletRequest request) {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String passwordRepeat = request.getParameter(PASSWORD_REPEAT);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        if (!isEmailValid(email)) {
            LOGGER.debug(MessageManager.getProperty("error.incorrect.email") + " " + email);
            request.setAttribute(INVALID_REGISTRATION_DATA,
                    MessageManager.getProperty("error.incorrect.email"));
            return Optional.empty();
        }

        if (!isPasswordValid(password)) {
            LOGGER.debug(MessageManager.getProperty("error.incorrect.password") + " " + password);
            request.setAttribute(INVALID_REGISTRATION_DATA,
                    MessageManager.getProperty("error.incorrect.password"));
            return Optional.empty();
        }

        if (!isRepeatPasswordValid(password, passwordRepeat)) {
            LOGGER.debug(MessageManager.getProperty("error.passwords.do.not.match") + " " + password
                    + " " + passwordRepeat);
            request.setAttribute(INVALID_REGISTRATION_DATA,
                    MessageManager.getProperty("error.passwords.do.not.match"));
            return Optional.empty();
        }

        if (!isFirstNameValid(firstName)) {
            LOGGER.debug(MessageManager.getProperty("error.incorrect.first.name") + " " + firstName);
            request.setAttribute(INVALID_REGISTRATION_DATA,
                    MessageManager.getProperty("error.incorrect.first.name"));
            return Optional.empty();
        }

        if (!isLastNameValid(lastName)) {
            LOGGER.debug(MessageManager.getProperty("error.incorrect.last.name") + " " + lastName);
            request.setAttribute(INVALID_REGISTRATION_DATA,
                    MessageManager.getProperty("error.incorrect.last.name"));
            return Optional.empty();
        }

        return Optional.ofNullable(User.builder()
                .withEmail(email)
                .withPassword(password)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withRole(new Role("user"))
                .build());
    }
}
