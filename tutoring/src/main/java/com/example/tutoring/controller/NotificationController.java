package com.example.tutoring.controller;

import static com.example.tutoring.utils.LoginValidator.isLogin;

import com.example.tutoring.dto.request.NotificationRequest;
import com.example.tutoring.models.Lesson;
import com.example.tutoring.models.Member;
import com.example.tutoring.security.UserDetailsImpl;
import com.example.tutoring.service.NotificationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {

  private final NotificationService notificationService;


  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody String token,
      @AuthenticationPrincipal UserDetailsImpl userSession) {
    notificationService.register(userSession.getUser().getId(), token);
    return ResponseEntity.ok().build();
  }

  private void sendLessonStartNotification(Lesson lesson) {
    List<Member> participants = lesson.getParticipants();
    for (Member participant : participants) {
      if (isLogin(participant)) {
        NotificationRequest notificationRequest = NotificationRequest.builder()
            .title("레슨 시작 알림")
            .message("레슨이 시작되었습니다.")
            .token(notificationService.getToken(participant.getId()))
            .build();

        notificationService.sendNotification(notificationRequest);
      }
    }
  }
  private void sendLessonParticipantNotification(Lesson lesson, Member participant) {
    NotificationRequest notificationRequest = NotificationRequest.builder()
        .title("수업 참여 알림")
        .message("수업에 참여하였습니다.")
        .token(notificationService.getToken(participant.getId()))
        .build();

    notificationService.sendNotification(notificationRequest);
  }

}