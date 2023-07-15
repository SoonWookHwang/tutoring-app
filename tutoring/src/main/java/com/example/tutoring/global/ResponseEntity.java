package com.example.tutoring.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@RequiredArgsConstructor
public record ResponseEntity<T>(HttpStatus httpStatus, T body, String msg) {

  public static <T> ResponseEntity<T> success(T body) {
    return new ResponseEntity<>(HttpStatus.OK, body, "성공");
  }

  public static <T> ResponseEntity<T> fail(Exception e) {
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST, null, e.getMessage());
  }

}
