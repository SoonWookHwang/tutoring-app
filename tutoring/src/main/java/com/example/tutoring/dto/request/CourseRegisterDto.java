package com.example.tutoring.dto.request;

import com.example.tutoring.models.enums.CourseType;
import com.example.tutoring.models.enums.LanguageType;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRegisterDto {
  private String title;
  //강의 타입 (채팅, 음성)
  private CourseType courseType;
  //강의 언어 (영어, 중국어)
  private LanguageType language;
  //강의 횟수
  private int lessonCount;
  //가격 (할인가능해야함)
  private BigDecimal price;
  //판매 시작 일자
  private LocalDate saleStartDate;
  //판매 종료 일자
  private LocalDate saleEndDate;
  //판매 상태
  private boolean isSaleEnded;

}
