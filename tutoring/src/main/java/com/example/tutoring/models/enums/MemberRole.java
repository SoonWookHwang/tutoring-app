package com.example.tutoring.models.enums;

import lombok.Getter;

@Getter
public enum MemberRole {
  STUDENT(Authority.STUDENT),
  TUTOR(Authority.TUTOR),
  ADMIN(Authority.ADMIN);
  private final String authority;

  MemberRole(String authority) {
    this.authority= authority;
  }
  public String getAuthority() {
    return this.authority;
  }


  public static class Authority {
    public static final String STUDENT = "ROLE_STUDENT";
    public static final String TUTOR = "ROLE_TUTOR";
    public static final String ADMIN = "ROLE_ADMIN";
  }
}



