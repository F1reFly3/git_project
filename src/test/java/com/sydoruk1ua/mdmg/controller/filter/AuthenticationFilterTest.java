package com.sydoruk1ua.mdmg.controller.filter;

import com.sydoruk1ua.mdmg.util.ConfigurationManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationFilterTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;


    @Test
    public void shouldReturnLoginPage() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn("/");
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.doFilter(request, response,
                filterChain);
        verify(response).sendRedirect(ConfigurationManager.getProperty("login.page.path"));
    }

    @Test
    public void shouldReturnErrorPage() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn("/asdf");
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.doFilter(request, response,
                filterChain);
        verify(response).sendRedirect(ConfigurationManager.getProperty("error.page.path"));
    }
}