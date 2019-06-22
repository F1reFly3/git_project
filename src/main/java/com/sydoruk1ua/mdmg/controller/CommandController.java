package com.sydoruk1ua.mdmg.controller;

import com.sydoruk1ua.mdmg.controller.command.*;
import com.sydoruk1ua.mdmg.model.dao.impl.AnswerDaoImpl;
import com.sydoruk1ua.mdmg.model.dao.impl.MessageDaoImpl;
import com.sydoruk1ua.mdmg.model.dao.impl.QuestionDaoImpl;
import com.sydoruk1ua.mdmg.model.dao.impl.UserDaoImpl;
import com.sydoruk1ua.mdmg.model.service.AnswerService;
import com.sydoruk1ua.mdmg.model.service.MessageService;
import com.sydoruk1ua.mdmg.model.service.QuestionService;
import com.sydoruk1ua.mdmg.model.service.UserService;
import com.sydoruk1ua.mdmg.model.service.impl.AnswerServiceImpl;
import com.sydoruk1ua.mdmg.model.service.impl.MessageServiceImpl;
import com.sydoruk1ua.mdmg.model.service.impl.QuestionServiceImpl;
import com.sydoruk1ua.mdmg.model.service.impl.UserServiceImpl;
import com.sydoruk1ua.mdmg.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandController {
    private static final Logger LOGGER = Logger.getLogger(CommandController.class);
    private static CommandController instance;
    private final UserService userService = new UserServiceImpl(new UserDaoImpl());
    private final QuestionService questionService = new QuestionServiceImpl(new QuestionDaoImpl());
    private final AnswerService answerService = new AnswerServiceImpl(new AnswerDaoImpl());
    private final MessageService messageService = new MessageServiceImpl(new MessageDaoImpl());


    private Map<String, Command> commands = new HashMap<>();

    private CommandController() {
        commands.put("login", new LoginCommand(userService));
        commands.put("logout", new LogoutCommand());
        commands.put("registration", new RegistrationCommand(userService));
        commands.put("questions", new GetAllQuestionsCommand(questionService));
        commands.put("language", new LanguageCommand());
        commands.put("users", new GetAllUsersCommand(userService));
        commands.put("add_question", new AddQuestionAnswerCommand(questionService, answerService));
        commands.put("game", new GameCommand(messageService));

        // Commands for redirect to concrete pages
        commands.put("login_page", (req, res) -> ConfigurationManager.getProperty("login.page.path"));
        commands.put("registration_page", (req, res) -> ConfigurationManager.getProperty("registration.page.path"));
        commands.put("main_page", (req, res) -> ConfigurationManager.getProperty("main.page.path"));
        commands.put("add_question_page", (req, res) -> ConfigurationManager.getProperty("question.add.page.path"));
    }


    public static CommandController getInstance() {
        if (instance == null) {
            instance = new CommandController();
        }

        return instance;
    }

    public Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        LOGGER.debug("action " + action);
        Command command = commands.get(action);
        LOGGER.debug("command " + command);
        if (command == null) {
            command = new NoCommand();
        }

        return command;
    }
}
