package com.sydoruk1ua.mdmg.controller.command;

import com.sydoruk1ua.mdmg.model.service.QuestionService;
import com.sydoruk1ua.mdmg.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetAllQuestionsCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(GetAllQuestionsCommand.class);
    private final QuestionService questionService;

    public GetAllQuestionsCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("questionsList", questionService.findAll());
        LOGGER.debug("Count of all questions: " + questionService.findAll().size());
        return ConfigurationManager.getProperty("questions.list.page.path");
    }
}
