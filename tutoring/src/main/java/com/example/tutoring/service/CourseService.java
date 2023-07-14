package com.example.tutoring.service;

import com.example.tutoring.dto.request.CourseRegisterDto;
import com.example.tutoring.models.Course;
import com.example.tutoring.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
  private final CourseRepository courseRepository;


  public Long registerCourse(CourseRegisterDto dto){
    Course newCourse = Course.builder()
        .title(dto.getTitle())
        .type(dto.getCourseType())
        .language(dto.getLanguage())
        .lessonCount(dto.getLessonCount())
        .price(dto.getPrice())
        .saleStartDate(dto.getSaleStartDate())
        .saleEndDate(dto.getSaleEndDate())
        .isSaleEnded(dto.isSaleEnded()).build();
    return courseRepository.save(newCourse).getId();
  }

}
