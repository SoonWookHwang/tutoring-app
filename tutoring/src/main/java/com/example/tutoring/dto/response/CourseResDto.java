package com.example.tutoring.dto.response;

import com.example.tutoring.models.enums.ClassType;
import com.example.tutoring.models.enums.LanguageType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResDto {
  private String tutorName;
  private String title;
  private LanguageType languageType;
  private ClassType classType;


}
