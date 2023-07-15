package com.example.tutoring.dto.request;

import com.example.tutoring.models.enums.ExpirationType;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubsRegisterDto {

  //구독권 이름
  private String title;
  //수강권 가격
  private BigDecimal price;
  //사용횟수
  private int usageCount;
  //사용기한
  private ExpirationType expirationType;
  //강좌 아이디
  private Long courseID;



}
