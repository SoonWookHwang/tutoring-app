package com.example.tutoring.service;

import com.example.tutoring.dto.request.SubsRegisterDto;
import com.example.tutoring.models.Subscription;
import com.example.tutoring.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
  private final SubscriptionRepository subscriptionRepository;

  //구독권 생성
  public Long registerSubscription(SubsRegisterDto dto) {
    Subscription newSubs = Subscription.builder()
        .title(dto.getTitle())
        .price(dto.getPrice())
        .usageCount(dto.getUsageCount())
        .expType(dto.getExpirationType())
        .build();
    return subscriptionRepository.save(newSubs).getId();
  }

}
