package com.sydoruk1ua.mdmg.controller.filter;

import com.sydoruk1ua.mdmg.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Filter checking if the request is processed by the
 * MainController
 *
 * @see com.sydoruk1ua.mdmg.controller.MainController
 * If not request is redirect to an error page
 */
@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class);
    private final Set<String> allowedPaths = Stream.of("/app").collect(Collectors.toCollection(HashSet::new));

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();

        if (uri.equals("/")) {
            response.sendRedirect(ConfigurationManager.getProperty("login.page.path"));
        } else {
            if (allowedPaths.contains(uri)) {
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect(ConfigurationManager.getProperty("error.page.path"));
            }
        }
    }
}

