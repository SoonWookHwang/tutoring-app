package com.example.tutoring.repository;

import com.example.tutoring.models.Course;
import com.example.tutoring.models.enums.ClassType;
import com.example.tutoring.models.enums.LanguageType;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
  //findByCourseTypeAndSaleStartDateBeforeAndSaleEndDateAfterAndIsSaleEndedFalse

  Optional<List<Course>> findByLanguageAndCourseTypeAndSaleStartDateBeforeAndSaleEndDateAfterAndIsSaleEndedFalse(
      LanguageType languageType, ClassType classType, LocalDate saleStartDate, LocalDate saleEndDate);

  Optional<List<Course>> findByCourseTypeAndSaleStartDateBeforeAndSaleEndDateAfterAndIsSaleEndedFalse(
      ClassType classType, LocalDate saleStartDate, LocalDate saleEndDate);








}
