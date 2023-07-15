package com.example.tutoring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


//실제로 멤버가 구매한 구독권 - 사용관리
@Entity
@Table(name = "subscription_member")
@NoArgsConstructor
@Getter
public class SubscriptionMember extends Timestamped{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // 수업 듣기 가능한 횟수
  @Column(name="usage_count")
  private int usageCount;
  // 구독권 만료일
  @Column(name = "expiration_date")
  private LocalDate expirationDate;
  // 사용가능 여부
  @Column(name = "is_active")
  private boolean isActive;
  // 실제 구매 가격
  @Column(name = "sale_price",nullable = false)
  private BigDecimal salePrice;
  // 사용시작 여부
  @Column(name = "is_used")
  private boolean isUsed;

  // 구독권 종류
  @ManyToOne
  @JoinColumn(name = "subscription_id", nullable = false)
  @JsonIgnore
  private Subscription subscription;

  // 구매한 학생
  @ManyToOne
  @JoinColumn(name = "member_id", nullable = false)
  @JsonIgnore
  private Member purchasedStudent;

  // 적용 이벤트
  @ManyToOne
  @JoinColumn(name = "event_id")
  @JsonIgnore
  private SpecialEvent specialEvent;

  @Builder
  public SubscriptionMember(int usageCount, LocalDate expirationDate, boolean isActive,
      BigDecimal salePrice, Subscription subscription, Member purchasedStudent,
      SpecialEvent specialEvent) {
    this.usageCount = usageCount;
    this.expirationDate = expirationDate;
    this.isActive = isActive;
    this.salePrice = salePrice;
    this.subscription = subscription;
    this.purchasedStudent = purchasedStudent;
    this.specialEvent = specialEvent;
    this.isUsed = false;
  }

  public void onceTakeLesson(SubscriptionMember ticket){
    ticket.usageCount -= 1;
    if(!ticket.isUsed){
      ticket.isUsed=true;
    }
  }
}
