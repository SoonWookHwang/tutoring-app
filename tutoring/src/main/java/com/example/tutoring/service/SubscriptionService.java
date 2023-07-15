package com.example.tutoring.service;

import com.example.tutoring.dto.request.SubsRegisterDto;
import com.example.tutoring.models.Course;
import com.example.tutoring.models.Subscription;
import com.example.tutoring.repository.CourseRepository;
import com.example.tutoring.repository.SubscriptionRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
  private final SubscriptionRepository subscriptionRepository;
  private final CourseRepository courseRepository;

  //구독권 생성
  public Long registerSubscription(SubsRegisterDto dto) {
    Course course = courseRepository.findById(dto.getCourseID())
        .orElseThrow(() -> new NoSuchElementException("해당 강좌가 존재하지 않습니다."));
    Subscription newSubs = Subscription.builder()
        .title(dto.getTitle())
        .price(dto.getPrice())
        .usageCount(dto.getUsageCount())
        .expType(dto.getExpirationType())
        .course(course)
        .build();
    return subscriptionRepository.save(newSubs).getId();
  }

}
