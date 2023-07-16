package com.example.tutoring.controller;

import static com.example.tutoring.global.ResponseEntity.fail;
import static com.example.tutoring.global.ResponseEntity.success;

import com.example.tutoring.dto.request.LessonRegisterDto;
import com.example.tutoring.dto.request.TakeLessonDto;
import com.example.tutoring.global.ResponseEntity;
import com.example.tutoring.models.enums.MemberRole.Authority;
import com.example.tutoring.security.UserDetailsImpl;
import com.example.tutoring.service.ChatRoomService;
import com.example.tutoring.service.ChatService;
import com.example.tutoring.service.LessonService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
  @Secured(Authority.TUTOR)
  public ResponseEntity<?> registerLesson(@RequestBody LessonRegisterDto dto) {
    try {
      return success(lessonService.registerLesson(dto));
    } catch (Exception e) {
      return fail(e);
    }
  }

  @GetMapping
  public ResponseEntity<?> takeLesson(@RequestBody TakeLessonDto dto,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    try {
      return success(lessonService.takeLesson(dto,userDetails.getUser()));
    }catch (Exception e){
      return fail(e);
    }
  }

  @PostMapping("/chat")
  @Secured(Authority.TUTOR)
  public ChatRoomService createRoom(@RequestParam Long lessonId) {
    return chatService.createRoom(lessonId);
  }

  @GetMapping("/chat")
  public List<ChatRoomService> findAllRooms() {
    return chatService.findAllRoom();
  }

}
