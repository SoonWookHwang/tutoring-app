package com.example.tutoring.handler;

import com.example.tutoring.dto.request.ChatDto;
import com.example.tutoring.service.ChatRoomService;
import com.example.tutoring.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

  private final ObjectMapper mapper;
  private final ChatService service;

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    String payload = message.getPayload();

    ChatDto chatMessage = mapper.readValue(payload, ChatDto.class);

    ChatRoomService room = service.findRoomById(chatMessage.getRoomId());

    room.handleAction(session, chatMessage, service);
  }
}
