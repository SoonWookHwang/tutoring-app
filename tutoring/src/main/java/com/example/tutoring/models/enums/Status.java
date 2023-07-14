package com.example.tutoring.models.enums;

public enum Status {
  USABLE("1"),
  USING("2"),
  USED("3"),
  EXPIRATION("4");

  private final String status;
  Status(String status){
    this.status= status;
  }
}
