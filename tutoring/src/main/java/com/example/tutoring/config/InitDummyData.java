package com.example.tutoring.config;

import com.example.tutoring.models.Course;
import com.example.tutoring.models.Member;
import com.example.tutoring.models.Subscription;
import com.example.tutoring.models.enums.CourseType;
import com.example.tutoring.models.enums.ExpirationType;
import com.example.tutoring.models.enums.LanguageType;
import com.example.tutoring.models.enums.MemberRole;
import com.example.tutoring.repository.CourseRepository;
import com.example.tutoring.repository.LessonRepository;
import com.example.tutoring.repository.MemberRepository;
import com.example.tutoring.repository.SpecialEventRepository;
import com.example.tutoring.repository.SubscriptionMemberRepository;
import com.example.tutoring.repository.SubscriptionRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDummyData {

  private final CourseRepository courseRepository;
  private final LessonRepository lessonRepository;
  private final MemberRepository memberRepository;
  private final SpecialEventRepository specialEventRepository;
  private final SubscriptionMemberRepository subscriptionMemberRepository;
  private final SubscriptionRepository subscriptionRepository;


  @Transactional
  @EventListener(ApplicationReadyEvent.class)
  public void init() {
    initMember();
    initCourse();
    initSubs();

  }

  public void initMember() {
    List<Member> dummyList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Member dummys;
      switch (i) {
        case 0, 1, 2, 3, 4:
          dummys = Member.builder()
              .username("student" + (i + 1))
              .password("password")
              .role(MemberRole.STUDENT).build();
          dummyList.add(dummys);
          break;
        case 5, 6, 7, 8:
          dummys = Member.builder()
              .username("tutor" + (i + 1))
              .password("password")
              .role(MemberRole.TUTOR).build();
          dummyList.add(dummys);
          break;
        case 9:
          dummys = Member.builder()
              .username("admin" + (i + 1))
              .password("password")
              .role(MemberRole.ADMIN).build();
          dummyList.add(dummys);
          break;
      }
    }
    memberRepository.saveAll(dummyList);
  }

  public void initCourse() {
    List<Course> dummyList = new ArrayList<>();

    for (int i = 0; i < 100; i++) {
      Random random = new Random();
      int randomDays = random.nextInt(30) + 1;
      int randomPrice = random.nextInt(3);
      int randomStatus = random.nextInt(2);
      boolean isSale = false;
      isSale = randomStatus == 0;
      BigDecimal basePrice = BigDecimal.valueOf(1000.00);
      if (randomPrice == 0) {
        basePrice = basePrice.add(BigDecimal.valueOf(200.00));
      } else if (randomPrice == 1) {
        basePrice = basePrice.add(BigDecimal.valueOf(500.00));
      } else {
        basePrice = basePrice.add(BigDecimal.valueOf(1000.00));
      }
      if (i % 7 == 0) {
        Course dummys = Course.builder()
            .title("chat-en-" + i)
            .type(CourseType.CHAT)
            .language(LanguageType.ENGLISH)
            .lessonCount(30)
            .price(basePrice)
            .saleStartDate(LocalDate.parse("2023-07-01").plusDays(randomDays))
            .saleEndDate(LocalDate.parse("2023-07-01").plusDays(randomDays))
            .isSaleEnded(isSale)
            .build();
        dummyList.add(dummys);
      } else if (i % 7 == 2) {
        Course dummys = Course.builder()
            .title("chat-ch-" + i)
            .type(CourseType.CHAT)
            .language(LanguageType.CHINESE)
            .lessonCount(60)
            .price(basePrice)
            .saleStartDate(LocalDate.parse("2023-07-01").plusDays(randomDays))
            .saleEndDate(LocalDate.parse("2023-07-01").plusDays(randomDays))
            .isSaleEnded(isSale)
            .build();
        dummyList.add(dummys);
      } else if (i % 7 == 4) {
        Course dummys = Course.builder()
            .title("voice-en-" + i)
            .type(CourseType.VOICE)
            .language(LanguageType.ENGLISH)
            .lessonCount(60)
            .price(basePrice)
            .saleStartDate(LocalDate.parse("2023-07-01").plusDays(randomDays))
            .saleEndDate(LocalDate.parse("2023-07-01").plusDays(randomDays))
            .isSaleEnded(isSale)
            .build();
        dummyList.add(dummys);
      } else if (i % 7 == 5) {
        Course dummys = Course.builder()
            .title("voice-ch-" + i)
            .type(CourseType.VOICE)
            .language(LanguageType.CHINESE)
            .lessonCount(60)
            .price(basePrice)
            .saleStartDate(LocalDate.parse("2023-07-01").plusDays(randomDays))
            .saleEndDate(LocalDate.parse("2023-07-01").plusDays(randomDays))
            .isSaleEnded(isSale)
            .build();
        dummyList.add(dummys);
      } else if (i % 7 == 6) {
        Course dummys = Course.builder()
            .title("both-en-" + i)
            .type(CourseType.BOTH)
            .language(LanguageType.ENGLISH)
            .lessonCount(60)
            .price(basePrice)
            .saleStartDate(LocalDate.parse("2023-07-01").plusDays(randomDays))
            .saleEndDate(LocalDate.parse("2023-07-01").plusDays(randomDays))
            .isSaleEnded(isSale)
            .build();
        dummyList.add(dummys);
      } else {
        Course dummys = Course.builder()
            .title("both-ch-" + i)
            .type(CourseType.BOTH)
            .language(LanguageType.CHINESE)
            .lessonCount(60)
            .price(basePrice)
            .saleStartDate(LocalDate.parse("2023-07-01").plusDays(randomDays))
            .saleEndDate(LocalDate.parse("2023-07-01").plusDays(randomDays))
            .isSaleEnded(isSale)
            .build();
        dummyList.add(dummys);
      }
      courseRepository.saveAll(dummyList);
    }
  }

  public void initSubs() {
    List<Subscription> dummyList = new ArrayList<>();
    for (int i = 0; i < 300; i++) {
      Random random = new Random();
      ExpirationType expType;
      int randomCourseId = random.nextInt(100) + 1;
      Course course = courseRepository.findById((long) randomCourseId).orElse(null);
      int randomType = random.nextInt(2);
      assert course != null;
      BigDecimal price = course.getPrice();
      if (randomType == 0) {
        expType = ExpirationType.EXP_30;
      } else {
        expType = ExpirationType.EXP_60;
      }
      int randomStatus = random.nextInt(2);
      boolean isSale = randomStatus == 0;

      Subscription dummys = new Subscription("subs-"+i+1,expType,Integer.parseInt(
          expType.getExpType()),price,course,isSale);
      dummyList.add(dummys);
    }
    subscriptionRepository.saveAll(dummyList);
  }
}
