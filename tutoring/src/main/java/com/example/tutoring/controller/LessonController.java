package com.example.tutoring.controller;

import static com.example.tutoring.global.ResponseEntity.*;

import com.example.tutoring.dto.request.ChatRoom;
import com.example.tutoring.dto.request.LessonRegisterDto;
import com.example.tutoring.global.ResponseEntity;
import com.example.tutoring.service.ChatService;
import com.example.tutoring.service.LessonService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {
  private final LessonService lessonService;
  private final ChatService chatService;

  @PostMapping
  public ResponseEntity<?> registerLesson(@RequestBody LessonRegisterDto dto) {
    try {
      return success(lessonService.registerLesson(dto));
    }catch (Exception e){
      return fail(e);
    }
  }

  @PostMapping("/chat")
  public ChatRoom createRoom(@RequestParam Long lessonId){
    return chatService.createRoom(lessonId);
  }

  @GetMapping("/chat")
  public List<ChatRoom> findAllRooms(){
    return chatService.findAllRoom();
  }





}
