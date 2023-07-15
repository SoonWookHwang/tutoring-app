package com.example.tutoring.controller;

import com.example.tutoring.dto.request.MemberSignupDto;
import com.example.tutoring.models.Member;
import com.example.tutoring.models.enums.MemberRole;
import com.example.tutoring.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
    return memberService.signup(dto);
  }

  @GetMapping
  public List<Member> getMembersByMemberRole(@RequestBody MemberRole memberRole){
    return memberService.getMembersByMemberRole(memberRole);
  }

}
