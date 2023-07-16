package com.example.tutoring.controller;

import com.example.tutoring.global.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

  @GetMapping("/access/error")
  public ResponseEntity<?> errorResponse(){
    return ResponseEntity.fail(new IllegalAccessException("권한이 없습니다."));
  }


}
