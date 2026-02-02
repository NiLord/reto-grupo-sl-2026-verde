package com.appbg.appbg.infrastructure.in.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> GeneralExceptionHandler(Exception ex) {
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage());
  }


}
