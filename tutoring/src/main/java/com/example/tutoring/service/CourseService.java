package com.example.tutoring.service;

import com.example.tutoring.dto.request.CourseRegisterDto;
import com.example.tutoring.models.Course;
import com.example.tutoring.models.enums.ClassType;
import com.example.tutoring.models.enums.LanguageType;
import com.example.tutoring.repository.CourseRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
  private final CourseRepository courseRepository;

  public Long registerCourse(CourseRegisterDto dto) {
    Course newCourse = Course.builder()
        .title(dto.getTitle())
        .type(dto.getClassType())
        .language(dto.getLanguage())
        .lessonCount(dto.getLessonCount())
        .price(dto.getPrice())
        .saleStartDate(dto.getSaleStartDate())
        .saleEndDate(dto.getSaleEndDate())
        .isSaleEnded(dto.isSaleEnded()).build();
    return courseRepository.save(newCourse).getId();
  }


  public List<Course> getActiveCourses(LanguageType languageType, ClassType classType) {
    LocalDate currentDate = LocalDate.now();
    if (languageType == null) {
      return courseRepository.findByCourseTypeAndSaleStartDateBeforeAndSaleEndDateAfterAndIsSaleEndedFalse(
              classType, currentDate, currentDate)
          .orElseThrow(() -> new NoSuchElementException("조건에 맞는 강좌가 없습니다."));
    } else {
      return courseRepository.findByLanguageAndCourseTypeAndSaleStartDateBeforeAndSaleEndDateAfterAndIsSaleEndedFalse(
              languageType, classType, currentDate, currentDate)
          .orElseThrow(() -> new NoSuchElementException("조건에 맞는 강좌가 없습니다."));
    }
  }

}
