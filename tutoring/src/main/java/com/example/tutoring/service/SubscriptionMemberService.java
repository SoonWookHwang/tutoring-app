package com.example.tutoring.service;

import com.example.tutoring.dto.request.PurchaseDto;
import com.example.tutoring.models.Lesson;
import com.example.tutoring.models.Member;
import com.example.tutoring.models.SpecialEvent;
import com.example.tutoring.models.Subscription;
import com.example.tutoring.models.SubscriptionMember;
import com.example.tutoring.models.enums.ClassType;
import com.example.tutoring.models.enums.LanguageType;
import com.example.tutoring.repository.LessonRepository;
import com.example.tutoring.repository.MemberRepository;
import com.example.tutoring.repository.SpecialEventRepository;
import com.example.tutoring.repository.SubscriptionMemberRepository;
import com.example.tutoring.repository.SubscriptionRepository;
import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscriptionMemberService {

  private final SubscriptionMemberRepository purchaseRepository;
  private final MemberRepository memberRepository;
  private final SubscriptionRepository subscriptionRepository;
  private final SpecialEventRepository specialEventRepository;
  private final LessonRepository lessonRepository;


  // 수강권 구매
  @Transactional
  public SubscriptionMember purchaseSubscription(PurchaseDto dto) throws AccessDeniedException{

    Member buyer = memberRepository.findById(dto.getStudentId())
        .orElseThrow(() -> new NoSuchElementException("해당 학생 아이디가 존재하지 않습니다."));
    Subscription subscription = subscriptionRepository.findById(dto.getSubscriptionId())
        .orElseThrow(() -> new NoSuchElementException("해당 수강권이 존재하지 않습니다."));
    BigDecimal originPrice = subscription.getPrice();

    if(subscription.getCourse().isSaleEnded()){
      throw new AccessDeniedException("구매할 수 없는 수강권입니다.");
    }
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

  // 수강권 사용 및 수업참여
  @Transactional
  public SubscriptionMember takeLesson(ClassType classType, LanguageType languageType,
      Long lessonId, Long memberId, Long ticketId) throws IllegalAccessException {
    Member student = memberRepository.findById(memberId)
        .orElseThrow(() -> new IllegalAccessException("로그인이 필요합니다"));
    Lesson lesson = lessonRepository.findById(lessonId)
        .orElseThrow(() -> new IllegalAccessException("존재하지 않은 수업입니다."));
    SubscriptionMember ticket = purchaseRepository.findById(ticketId)
        .orElseThrow(() -> new IllegalAccessException("존재하지 않은 수강권입니다."));

    checkTakeLesson(classType,languageType,lesson,student,ticket);

    ticket.onceTakeLesson(ticket);

    return ticket;
  }


  public void checkTakeLesson(ClassType classType, LanguageType languageType, Lesson lesson,
      Member student, SubscriptionMember ticket)
      throws IllegalAccessException {
    /* 시나리오
        1. 멤버가 학생이 아닌경우 // 학생이 구매한 이력이 없는 티켓일 경우
        2. 수강권이 수업의 강좌를 들을 수 있는 권한이 없는 경우//만료일이 지난 경우 || 사용가능 횟수가 0인경우
        3. 수업 정보 - 수업의 타입(채팅,음성)이 학생이 원하는 타입과 일치하지 않는 경우
     */
    if (student.getRole() != 1) {
      throw new IllegalAccessException("학생만 수강 신청이 가능합니다");
    }
    if (lesson.isFinished()) {
      throw new IllegalAccessException("종료된 수업입니다");
    }
    if (student != ticket.getPurchasedStudent()) {
      throw new IllegalAccessException("해당 수강권의 구매이력이 없습니다.");
    }
    if (ticket.getSubscription().getCourse() != lesson.getCourse()) {
      throw new IllegalAccessException("해당 수업을 참여할 권한이 없는 구독권입니다");
    }
    if (!ticket.isActive() || ticket.getExpirationDate().isBefore(LocalDate.now())
        || ticket.getUsageCount() == 0) {
      throw new IllegalAccessException("수강권의 사용권한이 만료되었습니다 (원인: 만료일경과, 수강 가능 횟수초과 등)");
    }
    if (classType != lesson.getClassType() && lesson.getClassType() != ClassType.BOTH) {
      throw new IllegalAccessException("원하시는 수업형태를 지원하지 않는 수업입니다.");
    }
    if (languageType != lesson.getLanguageType()) {
      throw new IllegalAccessException("원하시는 언어를 지원하지 않는 수업입니다.");
    }

  }

}
