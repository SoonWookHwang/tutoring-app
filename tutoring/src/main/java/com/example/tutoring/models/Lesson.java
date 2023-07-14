package com.example.tutoring.models;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lessons")
public class Lesson {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //수업일자
  private LocalDateTime date;

  //수강기간
  private LocalDateTime duration;

  //강의시작시간
  private LocalDateTime lessonStartTime;

  //수업 진행 상태
  private boolean isStarted;
  private boolean isFinished;

  //수강과정
  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;

  //수업 개설 튜터
  @ManyToOne
  @JoinColumn(name = "tutor_id")
  private Member tutor;







}
