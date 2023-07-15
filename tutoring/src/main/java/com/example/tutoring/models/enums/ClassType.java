package com.example.tutoring.models.enums;

import lombok.Getter;

@Getter
public enum ClassType {
  CHAT("CHAT"),
  VOICE("VOICE"),
  BOTH("BOTH");

  private final String classType;

  ClassType(String classType){
    this.classType=classType;
  }
}
