package com.example.tutoring.models.enums;

public enum CourseType {
  CHAT("1"),
  VOICE("2"),
  BOTH("3");

  private String courseType;

  CourseType(String courseType){
    this.courseType=courseType;
  }
}
