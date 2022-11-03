package com.corsarineri.portale.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class OrdineNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(OrdineNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String utenteNotFoundHandler(OrdineNotFoundException ex) {
    return ex.getMessage();
  }
}