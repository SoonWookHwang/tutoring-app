package com.example.tutoring.models;

import com.example.tutoring.models.enums.ClassType;
import com.example.tutoring.models.enums.LanguageType;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Course extends Timestamped{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "course_id")
  private Long id;
  //강의 이름
  private String title;
  //강의 타입 (채팅, 음성)
  @Enumerated(EnumType.STRING)
  private ClassType classType;
  //강의 언어 (영어, 중국어)
  @Enumerated(EnumType.STRING)
  private LanguageType language;
  //강의 횟수
  private int lessonCount;
  //정찰 가격 (할인가능해야함)
  private BigDecimal price;
  //판매 시작 일자
  private LocalDate saleStartDate;
  //판매 종료 일자
  private LocalDate saleEndDate;
  //판매 상태
  private boolean isSaleEnded;

  @OneToMany(mappedBy = "course")
  private List<Subscription> subscriptions;

  @OneToMany(mappedBy = "course")
  @JsonIgnore
  private List<Lesson> lessons;

  @Builder
  protected Course(String title, ClassType type, LanguageType language,
      int lessonCount, BigDecimal price, LocalDate saleStartDate, LocalDate saleEndDate,
      boolean isSaleEnded) {
    this.title = title;
    this.classType = type;
    this.language = language;
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
  public void changeIsSaleEnded(boolean isSaleEnded){
    this.isSaleEnded = isSaleEnded;
  }
  public void updatePrice(BigDecimal price){
    this.price = price;
  }

}
