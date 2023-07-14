package com.example.tutoring.models.enums;

import lombok.Getter;

@Getter
public enum MemberRole {
  STUDENT(1),
  TUTOR(2),
  ADMIN(0);
  public final int role;

  MemberRole(int role) {
    this.role = role;
  }
}



