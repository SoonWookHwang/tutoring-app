package com.example.tutoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

  @GetMapping("/chat/room")
  public String roompage(){
    return "/chatRoom.html";
  }

  @GetMapping("/lessons/page")
  public String lessonpage() {
    return "/createLesson.html";
  }
  @GetMapping("/chat/example")
  public String chatPage(){
    return "/chat.html";
  }
}
