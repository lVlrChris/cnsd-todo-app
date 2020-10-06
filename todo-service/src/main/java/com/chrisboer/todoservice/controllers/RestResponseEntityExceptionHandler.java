//package com.chrisboer.todoservice.controllers;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import javax.persistence.EntityNotFoundException;
//
//@ControllerAdvice
//public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<Object> handleValidationException(RuntimeException ex, WebRequest request) {
//        String responseBody = "One or more fields is invalid";
//        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//    @ExceptionHandler(EntityNotFoundException.class)
//    protected ResponseEntity<Object> handleEntityNotFoundException(RuntimeException ex, WebRequest request) {
//        String responseBody = "Entity with given id was not found";
//        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
//    }
//}
