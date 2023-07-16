package com.example.tutoring.controller;

import static com.example.tutoring.global.ResponseEntity.fail;
import static com.example.tutoring.global.ResponseEntity.success;

import com.example.tutoring.dto.request.CourseRegisterDto;
import com.example.tutoring.global.ResponseEntity;
import com.example.tutoring.models.enums.ClassType;
import com.example.tutoring.models.enums.LanguageType;
import com.example.tutoring.models.enums.MemberRole.Authority;
import com.example.tutoring.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

  private final CourseService courseService;

  //강좌 등록
  @PostMapping("/admin")
  @Secured(Authority.ADMIN)
  public Long registerCourse(@RequestBody CourseRegisterDto dto) {
    return courseService.registerCourse(dto);
  }

  @GetMapping
  public ResponseEntity<?> getActiveCourses(
      @RequestParam(required = false) LanguageType languageType,
      @RequestParam ClassType classType) {
    try {
      return success(courseService.getActiveCourses(languageType, classType));
    } catch (Exception e) {
      return fail(e);
    }
  }

  @PutMapping("/admin/sale/state")
  @Secured(Authority.ADMIN)
  public ResponseEntity<String> changeIsSaleEnded(@RequestBody Long courseId) {
    try {
      courseService.changeIsSaleEnded(courseId);
      return success("판매상태가 변경되었습니다.");
    } catch (Exception e) {
      return fail(e);
    }
  }

}
