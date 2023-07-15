package com.example.tutoring.service;

import com.example.tutoring.dto.request.PurchaseDto;
import com.example.tutoring.models.Member;
import com.example.tutoring.models.SpecialEvent;
import com.example.tutoring.models.Subscription;
import com.example.tutoring.models.bridge.SubscriptionMember;
import com.example.tutoring.repository.MemberRepository;
import com.example.tutoring.repository.SpecialEventRepository;
import com.example.tutoring.repository.SubscriptionMemberRepository;
import com.example.tutoring.repository.SubscriptionRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

  private final SubscriptionMemberRepository purchaseRepository;
  private final MemberRepository memberRepository;
  private final SubscriptionRepository subscriptionRepository;
  private final SpecialEventRepository specialEventRepository;

  @Transactional
  public SubscriptionMember purchaseSubscription(PurchaseDto dto) {

    Member buyer = memberRepository.findById(dto.getStudentId())
        .orElseThrow(() -> new NoSuchElementException("해당 학생 아이디가 존재하지 않습니다."));
    Subscription subscription = subscriptionRepository.findById(dto.getSubscriptionId())
        .orElseThrow(() -> new NoSuchElementException("해당 수강권이 존재하지 않습니다."));
    BigDecimal originPrice = subscription.getPrice();

    long usable = switch (subscription.getExpType()) {
      case "30" -> 30L;
      case "60" -> 60L;
      default -> throw new IllegalArgumentException("유효한 수강권 유효 기간이 아닙니다.");
    };
    SubscriptionMember purchase;

    //이벤트 적용된 수강권 구매
    if (dto.getEventId() != null) {
      SpecialEvent event = specialEventRepository.findById(dto.getEventId())
          .orElseThrow(() -> new NoSuchElementException("해당 이벤트가 존재하지 않습니다."));
      BigDecimal discountRate = event.getDiscount();
      BigDecimal salePrice = originPrice.subtract(discountRate);
      purchase = SubscriptionMember.builder()
          .purchasedStudent(buyer)
          .subscription(subscription)
          .expirationDate(LocalDate.now().plusDays(usable))
          .usageCount(Long.valueOf(usable).intValue())
          .isActive(true)
          .salePrice(salePrice)
          .specialEvent(event)
          .build();
    }
    // 이벤트 적용안한 수강권 구매
    else {
      purchase = SubscriptionMember.builder()
          .purchasedStudent(buyer)
          .subscription(subscription)
          .expirationDate(LocalDate.now().plusDays(usable))
          .usageCount(Long.valueOf(usable).intValue())
          .isActive(true)
          .salePrice(originPrice)
          .specialEvent(null)
          .build();
    }
    return purchaseRepository.save(purchase);

  }

}
