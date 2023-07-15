package com.example.tutoring.models;

import com.example.tutoring.models.bridge.SubscriptionMember;
import com.example.tutoring.models.enums.ExpirationType;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

  //판매 가능한 상태
  private boolean isSalesEnded;

  @ManyToOne
  @JoinColumn(name = "course_id")
  @JsonIgnore
  private Course course;

  //판매 이력
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "subscription")
  @JsonIgnore
  private List<SubscriptionMember> salesRecord;


  @Builder
  public Subscription(String title, ExpirationType expType, int usageCount, BigDecimal price,Course course) {
    this.title = title;
    this.expType = expType.getExpType();
    this.usageCount = usageCount;
    this.price = price;
    this.course = course;
  }

  //테스트 더미데이터 생성용 생성자
  public Subscription(String title, ExpirationType expType, int usageCount, BigDecimal price,Course course, boolean isSalesEnded) {
    this.title = title;
    this.expType = expType.getExpType();
    this.usageCount = usageCount;
    this.price = price;
    this.course = course;
    this.isSalesEnded = isSalesEnded;
  }

  public void updateIsSales(boolean isSales){
    this.isSalesEnded = isSales;
  }
}
