package com.example.tutoring.models.enums;

import lombok.Getter;

@Getter
public enum ClassType {
  CHAT("1"),
  VOICE("2"),
  BOTH("3");

  private final String courseType;

  ClassType(String courseType){
    this.courseType=courseType;
  }
}
