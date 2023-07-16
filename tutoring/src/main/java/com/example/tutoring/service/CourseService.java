package com.example.tutoring.service;

import com.example.tutoring.dto.request.CourseRegisterDto;
import com.example.tutoring.models.Course;
import com.example.tutoring.models.Member;
import com.example.tutoring.models.enums.ClassType;
import com.example.tutoring.models.enums.LanguageType;
import com.example.tutoring.repository.CourseRepository;
import com.example.tutoring.repository.MemberRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseService {
  private final CourseRepository courseRepository;
  private final MemberRepository memberRepository;

  @Transactional
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
      return courseRepository.findByClassTypeAndSaleStartDateBeforeAndSaleEndDateAfterAndIsSaleEndedFalse(
              classType, currentDate, currentDate)
          .orElseThrow(() -> new NoSuchElementException("조건에 맞는 강좌가 없습니다."));
    } else {
      return courseRepository.findByLanguageAndClassTypeAndSaleStartDateBeforeAndSaleEndDateAfterAndIsSaleEndedFalse(
              languageType, classType, currentDate, currentDate)
          .orElseThrow(() -> new NoSuchElementException("조건에 맞는 강좌가 없습니다."));
    }
  }

  @Transactional
  public void changeIsSaleEnded(Long courseId)throws IllegalAccessException{
//    Member admin = memberRepository.findById(memberId)
//        .orElseThrow(() -> new NoSuchElementException("존재하지 않은 계정입니다."));
//    if (admin.getRole() != 0) {
//      throw new IllegalAccessException("관리자만 강의의 판매상태를 변경할 수 있습니다");
//    }
    Course course = courseRepository.findById(courseId)
        .orElseThrow(() -> new NoSuchElementException("존재하지 않은 강좌입니다"));
    course.changeIsSaleEnded(!course.isSaleEnded());
  }

}
