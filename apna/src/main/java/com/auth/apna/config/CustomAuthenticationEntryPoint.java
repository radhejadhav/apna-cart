package com.auth.apna.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {

    Logger logger = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
        logger.error("User not authenticated:: "+ request.getRemoteUser());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized user");
    }
}
