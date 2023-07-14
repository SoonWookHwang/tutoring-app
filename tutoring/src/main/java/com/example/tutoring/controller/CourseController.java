package com.example.tutoring.controller;

import com.example.tutoring.dto.request.CourseRegisterDto;
import com.example.tutoring.service.CourseService;
import lombok.RequiredArgsConstructor;
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
  @PostMapping("/course")
  public Long registerCourse(@RequestBody CourseRegisterDto dto){
    return courseService.registerCourse(dto);
  }
}
