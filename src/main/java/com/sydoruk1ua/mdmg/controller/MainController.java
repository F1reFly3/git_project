package com.sydoruk1ua.mdmg.controller;

import com.sydoruk1ua.mdmg.controller.command.Command;
import com.sydoruk1ua.mdmg.util.ConfigurationManager;
import com.sydoruk1ua.mdmg.util.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/*")
public class MainController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(MainController.class);
    private static final CommandController COMMAND_CONTROLLER = CommandController.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String page;
        LOGGER.debug("entered");
        try {
            Command command = COMMAND_CONTROLLER.getCommand(request);
            page = command.execute(request, response);
        } catch (ServletException e) {
            LOGGER.error(e.getMessage(), e);

            request.setAttribute("errorMessage", MessageManager.getProperty("servlet.exception.error.message"));
            page = ConfigurationManager.getProperty("error.page.path");
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);

            request.setAttribute("errorMessage", MessageManager.getProperty("io.exception.error.message"));
            page = ConfigurationManager.getProperty("error.page.path");
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}