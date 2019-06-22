package com.sydoruk1ua.mdmg.controller.command;

import com.sydoruk1ua.mdmg.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LanguageCommand implements Command {
    private static final String LANG = "lang";
    private static final String EN = "en";
    private static final String RU = "ru";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String lang = request.getParameter(LANG);

        if (RU.equals(lang)) {
            request.getSession().setAttribute(LANG, RU);
        } else {
            request.getSession().setAttribute(LANG, EN);
        }
        return ConfigurationManager.getProperty("main.page.path");
    }
}
