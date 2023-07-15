package com.example.tutoring.controller;

import static com.example.tutoring.global.ResponseEntity.*;

import com.example.tutoring.dto.request.LessonRegisterDto;
import com.example.tutoring.global.ResponseEntity;
import com.example.tutoring.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {
  private final LessonService lessonService;

  @PostMapping
  public ResponseEntity<?> registerLesson(@RequestBody LessonRegisterDto dto) {
    try {
      return success(lessonService.registerLesson(dto));
    }catch (Exception e){
      return fail(e);
    }
  }

}
