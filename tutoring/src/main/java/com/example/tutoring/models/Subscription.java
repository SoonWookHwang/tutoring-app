package com.example.tutoring.models;

import com.example.tutoring.models.bridge.SubscriptionMember;
import com.example.tutoring.models.enums.ExpirationType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subscriptions")
@Getter
@NoArgsConstructor
public class Subscription {

  @Id
  @Column(name = "subs_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  //수강권 이름
  @Column(name = "subs_title", nullable = false)
  private String title;

  //사용기한
  private String expType;
  //사용횟수
  private int usageCount;
  //수강권 가격
  private BigDecimal price;

  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;

  //판매 이력
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "subscription")
  private List<SubscriptionMember> salesRecord;


  @Builder
  public Subscription(String title, ExpirationType expType, int usageCount, BigDecimal price) {
    this.title = title;
    this.expType = expType.getExpType();
    this.usageCount = usageCount;
    this.price = price;
  }
}
