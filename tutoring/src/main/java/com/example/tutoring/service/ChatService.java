package com.example.tutoring.service;

import com.example.tutoring.dto.request.ChatRoom;
import com.example.tutoring.models.Lesson;
import com.example.tutoring.repository.LessonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
@Data
@Service
@RequiredArgsConstructor
public class ChatService {
  private final LessonRepository lessonRepository;
  private final ObjectMapper mapper;
  private Map<String, ChatRoom> chatRooms;

  @PostConstruct
  private void init() {
    chatRooms = new LinkedHashMap<>();
  }

  public List<ChatRoom> findAllRoom(){
    return new ArrayList<>(chatRooms.values());
  }
  public ChatRoom findRoomById(String roomId){
    return chatRooms.get(roomId);
  }

  public ChatRoom createRoom(Long lessonId) {
    String roomId = UUID.randomUUID().toString(); // 랜덤한 방 아이디 생성

    Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
    // Builder 를 이용해서 ChatRoom 을 Building
    assert lesson != null;
    ChatRoom room = ChatRoom.builder()
        .roomId(roomId)
        .name(lesson.getCourse().getTitle()+"/ 강사 : "+ lesson.getTutor().getUsername())
        .build();
    chatRooms.put(roomId, room); // 랜덤 아이디와 room 정보를 Map 에 저장
    return room;
  }

  public <T> void sendMessage(WebSocketSession session, T message) {
    try{
      session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
  }
}