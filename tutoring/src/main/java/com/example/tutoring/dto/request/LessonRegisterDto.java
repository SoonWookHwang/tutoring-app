package com.example.tutoring.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonRegisterDto {

  private String tutorUsername;

  private Long courseId;

  @JsonFormat(pattern = "yyyyMMdd-HH-mm")
  private LocalDateTime date;

  //수강기간
  @JsonFormat(pattern = "yyyyMMdd-HH-mm")
  private LocalDateTime duration;

  //강의시작시간
  @JsonFormat(pattern = "yyyyMMdd-HH-mm")
  private LocalDateTime lessonStartTime;


}
