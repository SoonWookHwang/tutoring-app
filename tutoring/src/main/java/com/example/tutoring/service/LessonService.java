package com.example.tutoring.service;

import static com.example.tutoring.utils.LessonValidator.checkTakeLesson;

import com.example.tutoring.dto.request.LessonRegisterDto;
import com.example.tutoring.dto.request.TakeLessonDto;
import com.example.tutoring.models.Course;
import com.example.tutoring.models.Lesson;
import com.example.tutoring.models.Member;
import com.example.tutoring.models.SubscriptionMember;
import com.example.tutoring.models.enums.ClassType;
import com.example.tutoring.repository.CourseRepository;
import com.example.tutoring.repository.LessonRepository;
import com.example.tutoring.repository.MemberRepository;
import com.example.tutoring.repository.SubscriptionMemberRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LessonService {

  private final LessonRepository lessonRepository;
  private final MemberRepository memberRepository;
  private final CourseRepository courseRepository;
  private final SubscriptionMemberRepository subscriptionMemberRepository;


  public Long registerLesson(LessonRegisterDto dto) throws IllegalAccessException {
    Member tutor = memberRepository.findByUsername(dto.getTutorUsername())
        .orElseThrow(() -> new NoSuchElementException("등록된 튜터가 아닙니다."));
    if(!tutor.getRole().getAuthority().equals("ROLE_TUTOR")){
      throw new IllegalAccessException("튜터로 등록된 사용자만 개설 가능합니다");
    }
    Course course = courseRepository.findById(dto.getCourseId())
        .orElseThrow(() -> new NoSuchElementException("해당 강좌가 존재하지 않습니다."));

    if(!dto.getLanguageType().equals(course.getLanguage())){
      throw new IllegalArgumentException("해당 강좌에서는 수업 개설이 허용되지 않은 언어입니다.");
    }
    if(!dto.getClassType().equals(course.getClassType())&&course.getClassType()!= ClassType.BOTH){
      throw new IllegalAccessException("해당 강좌에서는 수업 개설이 허용되지 않은 수업방식입니다.");
    }
    Lesson newLesson = Lesson.builder()
        .classType(dto.getClassType())
        .languageType(dto.getLanguageType())
        .date(dto.getDate())
        .duration(dto.getDuration())
        .lessonStartTime(dto.getLessonStartTime())
        .isStarted(false)
        .isFinished(false)
        .tutor(tutor)
        .course(course)
        .build();
    return lessonRepository.save(newLesson).getId();
  }

  // 수강권 사용 및 수업참여
  @Transactional
  public String takeLesson(TakeLessonDto dto,Member student) throws IllegalAccessException {
    Lesson lesson = lessonRepository.findById(dto.getLessonId())
        .orElseThrow(() -> new IllegalAccessException("존재하지 않은 수업입니다."));
    SubscriptionMember ticket = subscriptionMemberRepository.findById(dto.getTicketId())
        .orElseThrow(() -> new IllegalAccessException("존재하지 않은 수강권입니다."));
    checkTakeLesson(dto.getClassType(),dto.getLanguageType(),lesson,student,ticket);
    ticket.onceTakeLesson(ticket);
    lesson.addParticipants(student);
    return "수업을 수강합니다";
  }



}
