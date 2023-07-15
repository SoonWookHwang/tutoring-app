package com.example.tutoring.global;

import org.springframework.http.HttpStatus;


public record ResponseEntity<T>(HttpStatus httpStatus, T body, String msg) {

  public static <T> ResponseEntity<T> success(T body) {
    return new ResponseEntity<>(HttpStatus.OK, body, "성공");
  }

  public static <T> ResponseEntity<T> fail(Exception e) {
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST, null, e.getMessage());
  }

}
