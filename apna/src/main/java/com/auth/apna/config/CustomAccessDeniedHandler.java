package com.auth.apna.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException exc) throws IOException {
        logger.error("Access denied for user:: "+ request.getRemoteUser());
        response.addHeader("access_denied_reason", "not_authorized");
        response.sendError(403, "Access Denied");
    }
}
