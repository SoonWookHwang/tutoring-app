package com.example.tutoring.models;

import com.example.tutoring.models.enums.ClassType;
import com.example.tutoring.models.enums.LanguageType;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lessons")
@NoArgsConstructor
@Getter
public class Lesson extends Timestamped{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //수업일자
  private LocalDateTime date;
  //수강기간
  private LocalDateTime duration;
  //강의시작시간
  private LocalDateTime lessonStartTime;
  //수업 방식
  private ClassType classType;
  //수업 언어
  private LanguageType languageType;
  //수업 진행 상태
  private boolean isStarted;
  private boolean isFinished;

  //수강과정
  @ManyToOne
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  //수업 개설 튜터
  @ManyToOne
  @JoinColumn(name = "tutor_id", nullable = false)
  private Member tutor;

  @Builder
  public Lesson(ClassType classType, LanguageType languageType, LocalDateTime date,
      LocalDateTime duration, LocalDateTime lessonStartTime,
      boolean isStarted, boolean isFinished, Course course, Member tutor) {
    this.classType = classType;
    this.languageType = languageType;
    this.date = date;
    this.duration = duration;
    this.lessonStartTime = lessonStartTime;
    this.isStarted = isStarted;
    this.isFinished = isFinished;
    this.course = course;
    this.tutor = tutor;
  }
}
