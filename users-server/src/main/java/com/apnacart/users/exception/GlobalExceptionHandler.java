package com.apnacart.users.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(HttpServletRequest request, Exception ex){

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage()+"Advice");
        errorResponse.setUrl(request.getRequestURL().toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(HttpServletRequest request, Exception ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage()+"Advice");
        errorResponse.setUrl(request.getRequestURL().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
