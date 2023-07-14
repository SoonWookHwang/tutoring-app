package com.example.tutoring.controller;

import com.example.tutoring.dto.request.MemberSignupDto;
import com.example.tutoring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;

  @PostMapping("/signup")
  public Long signup(@RequestBody MemberSignupDto dto){
    System.out.println(dto.getUsername());
    System.out.println(dto.getPassword());
    return memberService.signup(dto);
  }

}
