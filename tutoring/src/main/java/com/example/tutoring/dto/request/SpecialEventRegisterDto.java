package com.example.tutoring.dto.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecialEventRegisterDto {

  //이벤트 명
  private String title;

  // 할인율
  private BigDecimal discount;


}
