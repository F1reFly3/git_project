package com.sydoruk1ua.mdmg.controller.command;

import com.sydoruk1ua.mdmg.model.entity.Message;
import com.sydoruk1ua.mdmg.model.service.MessageService;
import com.sydoruk1ua.mdmg.util.AttributeManager;
import com.sydoruk1ua.mdmg.util.ConfigurationManager;
import com.sydoruk1ua.mdmg.util.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.sydoruk1ua.mdmg.util.Validator.isEmailValid;
import static com.sydoruk1ua.mdmg.util.Validator.isMessageValid;

public class GameCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(GameCommand.class);

    private static final String GAME_PAGE_PATH = "game.page.path";
    private static final String INVALID_MESSAGE_FORMAT = AttributeManager.getProperty("invalid.message.format");

    private final MessageService messageService;

    public GameCommand(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Optional<Message> optionalMessage = getValidMessage(req);
        if (!optionalMessage.isPresent()) {
            req.setAttribute("messageList", messageService.findAll());
            return ConfigurationManager.getProperty(GAME_PAGE_PATH);
        }
        messageService.create(optionalMessage.get());

        req.setAttribute("messageList", messageService.findAll());

        return ConfigurationManager.getProperty(GAME_PAGE_PATH);
    }

    private Optional<Message> getValidMessage(HttpServletRequest request) {
        String userEmail = request.getParameter("nickname");
        String msg = request.getParameter("msg");
        if (!isEmailValid(userEmail)) {
            LOGGER.debug(MessageManager.getProperty("error.incorrect.email") + " " + userEmail);
            return Optional.empty();
        }
        if (!isMessageValid(msg)) {
            LOGGER.debug(MessageManager.getProperty("error.incorrect.message") + " " + msg);
            request.setAttribute(INVALID_MESSAGE_FORMAT,
                    MessageManager.getProperty("error.incorrect.message"));
            return Optional.empty();
        }
        return Optional.of(new Message(userEmail, msg));
    }
}
