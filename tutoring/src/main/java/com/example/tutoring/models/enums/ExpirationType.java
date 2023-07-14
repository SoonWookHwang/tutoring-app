package com.example.tutoring.models.enums;

import lombok.Getter;

@Getter
public enum ExpirationType {
  EXP_30("30"),
  EXP_60("60");

  private final String expType;

  ExpirationType(String expType){
    this.expType = expType;
  }
}
