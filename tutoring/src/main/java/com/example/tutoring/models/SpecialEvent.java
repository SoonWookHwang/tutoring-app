package com.example.tutoring.models;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "events")
@Getter
@NoArgsConstructor
public class SpecialEvent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "event_id")
  private Long id;

  // 이벤트 이름
  private String title;

  // 할인율
  private BigDecimal discount;

  @Builder
  public SpecialEvent(String title, BigDecimal discount) {
    this.title = title;
    this.discount = discount;
  }
}
