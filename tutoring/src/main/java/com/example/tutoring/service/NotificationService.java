package com.example.tutoring.service;

import com.example.tutoring.dto.request.NotificationRequest;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

  private final Map<Long, String> tokenMap = new HashMap<>();

  public void register(final Long userId, final String token) {
    tokenMap.put(userId, token);
  }

  public String getToken(Long userId) {
    return tokenMap.get(userId);
  }

  public void sendNotification(NotificationRequest request) {
    String title = request.getTitle();
    String message = request.getMessage();
    String token = request.getToken();

    // Firebase 메시지 생성
    Message firebaseMessage = Message.builder()
        .setNotification(new Notification(title, message))
        .setToken(token)
        .build();

    try {
      // Firebase 알림 전송
      FirebaseMessaging.getInstance().send(firebaseMessage);
    } catch (FirebaseMessagingException e) {
      log.warn(e.getMessage());
    }
  }
}
