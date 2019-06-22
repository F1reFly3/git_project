package com.sydoruk1ua.mdmg.controller.command;

import com.sydoruk1ua.mdmg.model.entity.Role;
import com.sydoruk1ua.mdmg.model.entity.User;
import com.sydoruk1ua.mdmg.model.service.UserService;
import com.sydoruk1ua.mdmg.util.ConfigurationManager;
import com.sydoruk1ua.mdmg.util.PasswordEncryptor;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PasswordEncryptor.class)
public class LoginCommandTest {

    @Mock
    private UserService userService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Ignore
    @Test
    public void shouldReturnLoginPage() {
        when(request.getParameter("email")).thenReturn("");
        LoginCommand loginCommand = new LoginCommand(userService);
        String actual = loginCommand.execute(request, response);
        String expected = ConfigurationManager.getProperty("login.page.path");
        assertEquals(expected, actual);
    }

    @Ignore
    @Test
    public void shouldReturnMainPage() {
        PowerMockito.mockStatic(PasswordEncryptor.class);
        when(request.getParameter("email")).thenReturn("user1@gmail.com");
        when(request.getParameter("password")).thenReturn("user1pasS1!");
        when(request.getSession()).thenReturn(session);
        User user = User.builder()
                .withEmail("user1@gmail.com")
                .withPassword("user1pasS1!")
                .withFirstName("User1")
                .withLastName("UserLast1")
                .withRole(new Role(2, "user"))
                .build();
        when(userService.findByEmail("user1@gmail.com")).thenReturn(Optional.ofNullable(user));
        PowerMockito.when(PasswordEncryptor.encrypt("user1pasS1!")).thenReturn("d35d3ba516a1827bd24ffec29ed1a438");

        LoginCommand loginCommand = new LoginCommand(userService);
        String actual = loginCommand.execute(request, response);

        String expected = ConfigurationManager.getProperty("main.page.path");
        assertEquals(expected, actual);
    }
}

