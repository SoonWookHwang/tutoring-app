package com.example.tutoring.dto.request;

import com.example.tutoring.models.enums.ClassType;
import com.example.tutoring.models.enums.LanguageType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TakeLessonDto {
  private ClassType classType;
  private LanguageType languageType;
  private Long lessonId;
  private Long ticketId;

}
