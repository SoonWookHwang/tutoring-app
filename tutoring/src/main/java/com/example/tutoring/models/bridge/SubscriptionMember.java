package com.example.tutoring.models.bridge;

import com.example.tutoring.models.Member;
import com.example.tutoring.models.SpecialEvent;
import com.example.tutoring.models.Subscription;
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


//실제로 멤버가 구매한 구독권 - 사용관리
@Entity
@Table(name = "subscription_member")
public class SubscriptionMember {
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

  // 구독권 종류
  @ManyToOne
  @JoinColumn(name = "subscription_id", nullable = false)
  private Subscription subscription;

  // 구매한 학생
  @ManyToOne
  @JoinColumn(name = "member_id", nullable = false)
  private Member purchasedStudent;

  // 적용 이벤트
  @ManyToOne
  @JoinColumn(name = "event_id")
  private SpecialEvent specialEvent;

}
