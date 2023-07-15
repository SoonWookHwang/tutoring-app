package com.example.tutoring.service;

import com.example.tutoring.dto.request.CourseRegisterDto;
import com.example.tutoring.models.Course;
import com.example.tutoring.models.enums.CourseType;
import com.example.tutoring.models.enums.LanguageType;
import com.example.tutoring.repository.CourseRepository;
import com.example.tutoring.repository.SubscriptionRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

  private final CourseRepository courseRepository;
  private final SubscriptionRepository subscriptionRepository;


  public Long registerCourse(CourseRegisterDto dto) {
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


  public List<Course> getActiveCourses(LanguageType languageType, CourseType courseType) {
    LocalDate currentDate = LocalDate.now();
    if (languageType == null) {
      return courseRepository.findByCourseTypeAndSaleStartDateAfterAndSaleEndDateBeforeAndIsSaleEndedFalse(
              courseType, currentDate, currentDate)
          .orElseThrow(() -> new NoSuchElementException("조건에 맞는 강좌가 없습니다."));
    } else {
      return courseRepository.findByLanguageAndCourseTypeAndSaleStartDateAfterAndSaleEndDateBeforeAndIsSaleEndedFalse(
              languageType, courseType, currentDate, currentDate)
          .orElseThrow(() -> new NoSuchElementException("조건에 맞는 강좌가 없습니다."));
    }
  }

}
