package com.example.tutoring.service;

import com.example.tutoring.dto.request.LessonRegisterDto;
import com.example.tutoring.models.Course;
import com.example.tutoring.models.Lesson;
import com.example.tutoring.models.Member;
import com.example.tutoring.repository.CourseRepository;
import com.example.tutoring.repository.LessonRepository;
import com.example.tutoring.repository.MemberRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {

  private final LessonRepository lessonRepository;
  private final MemberRepository memberRepository;
  private final CourseRepository courseRepository;


  public Long registerLesson(LessonRegisterDto dto) throws IllegalAccessException {
    Member tutor = memberRepository.findByUsername(dto.getTutorUsername())
        .orElseThrow(() -> new NoSuchElementException("등록된 튜터가 아닙니다."));
    if(tutor.getRole()!=2){
      throw new IllegalAccessException("튜터로 등록된 사용자만 개설 가능합니다");
    }
    Course course = courseRepository.findById(dto.getCourseId())
        .orElseThrow(() -> new NoSuchElementException("해당 강좌가 존재하지 않습니다."));

    Lesson newLesson = Lesson.builder()
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

}
