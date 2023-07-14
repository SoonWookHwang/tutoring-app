package com.example.tutoring.models;

import com.example.tutoring.models.enums.CourseType;
import com.example.tutoring.models.enums.LanguageType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@Getter
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "course_id")
  private Long id;
  //강의 이름
  private String title;
  //강의 타입 (채팅, 음성)
  @Enumerated(EnumType.STRING)
  private CourseType courseType;
  //강의 언어 (영어, 중국어)
  @Enumerated(EnumType.STRING)
  private LanguageType language;
  //만료일자
  private int duration;
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


  @OneToMany(mappedBy = "course")
  private List<Lesson> lessons;

  @Builder
  protected Course(String title, CourseType type, LanguageType language, int duration,
      int lessonCount, BigDecimal price, LocalDate saleStartDate, LocalDate saleEndDate,
      boolean isSaleEnded) {
    this.title = title;
    this.courseType = type;
    this.language = language;
    this.duration = duration;
    this.lessonCount = lessonCount;
    this.price = price;
    this.saleStartDate = saleStartDate;
    this.saleEndDate = saleEndDate;
    this.isSaleEnded = isSaleEnded;
  }
  public void updateSaleDate(LocalDate saleStartDate, LocalDate saleEndDate){
    this.saleStartDate = saleStartDate;
    this.saleEndDate = saleEndDate;
  }
  public void updateIsSaleEnded(boolean isSaleEnded){
    this.isSaleEnded = isSaleEnded;
  }
  public void updatePrice(BigDecimal price){
    this.price = price;
  }

}
