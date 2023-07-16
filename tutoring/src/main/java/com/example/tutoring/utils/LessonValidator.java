package com.example.tutoring.utils;

import com.example.tutoring.models.Lesson;
import com.example.tutoring.models.Member;
import com.example.tutoring.models.SubscriptionMember;
import com.example.tutoring.models.enums.ClassType;
import com.example.tutoring.models.enums.LanguageType;
import java.time.LocalDate;

public class LessonValidator {

  public static void checkTakeLesson(ClassType classType, LanguageType languageType, Lesson lesson,
      Member student, SubscriptionMember ticket)
      throws IllegalAccessException {
    /* 시나리오
        1. 멤버가 학생이 아닌경우 // 학생이 구매한 이력이 없는 티켓일 경우
        2. 수강권이 수업의 강좌를 들을 수 있는 권한이 없는 경우//만료일이 지난 경우 || 사용가능 횟수가 0인경우
        3. 수업 정보 - 수업의 타입(채팅,음성)이 학생이 원하는 타입과 일치하지 않는 경우
     */
    if (!student.getRole().getAuthority().equals("ROLE_STUDENT")) {
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
