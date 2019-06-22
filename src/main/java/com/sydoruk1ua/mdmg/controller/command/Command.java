package com.sydoruk1ua.mdmg.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@FunctionalInterface
public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
