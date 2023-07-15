package com.example.tutoring.dto.request;


import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseDto {
  @NotNull
  private Long studentId;
  @NotNull
  private Long subscriptionId;

  private Long eventId;

}
