package com.example.tutoring.controller;

import static com.example.tutoring.global.ResponseEntity.*;

import com.example.tutoring.dto.request.CourseRegisterDto;
import com.example.tutoring.global.ResponseEntity;
import com.example.tutoring.models.enums.CourseType;
import com.example.tutoring.models.enums.LanguageType;
import com.example.tutoring.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {
  private final CourseService courseService;

  //강좌 등록
  @PostMapping
  public Long registerCourse(@RequestBody CourseRegisterDto dto){
    return courseService.registerCourse(dto);
  }

  @GetMapping
  public ResponseEntity<?> getActiveCourses(LanguageType languageType, CourseType courseType){
    try {
      return sucess(courseService.getActiveCourses(languageType, courseType));
    }catch (Exception e){
      return fail(e);
    }
  }
}
