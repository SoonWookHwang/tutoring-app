package com.example.tutoring.models.enums;

import lombok.Getter;

@Getter
public enum LanguageType {
  ENGLISH("1"),
  CHINESE("2");

  public final String languageType;

  LanguageType(String languageType) {
    this.languageType = languageType;
  }
}
