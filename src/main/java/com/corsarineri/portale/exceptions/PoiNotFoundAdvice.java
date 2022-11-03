package com.corsarineri.portale.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class PoiNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(PoiNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String indizioNotFoundHandler(PoiNotFoundException ex) {
    return ex.getMessage();
  }
}