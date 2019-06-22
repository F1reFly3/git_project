package com.sydoruk1ua.mdmg.controller.filter;

import com.sydoruk1ua.mdmg.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter check the rights to access to specific page
 */
@WebFilter(filterName = "AuthorizationFilter")
public class AuthorizationFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String command = req.getParameter("command");

        HttpSession session = req.getSession(false);
        String role = null;
        if (session != null) {
            role = (String) req.getSession().getAttribute("userRole");
        }
        if (command != null && !command.contains("login") && !command.contains("registration")
                && !command.contains("main") && !command.equals("language")) {
            if ("user".equals(role) || "judge".equals(role)) {
                if (command.contains("game") || command.contains("language") || command.contains("logout")) {
                    chain.doFilter(req, response);
                } else {
                    resp.sendRedirect(ConfigurationManager.getProperty("403.page.path"));
                }
            } else if ("admin".equals(role)) {
                chain.doFilter(req, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
