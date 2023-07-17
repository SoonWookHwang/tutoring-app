package com.example.tutoring.controller;

import com.example.tutoring.dto.request.MemberSignupDto;
import com.example.tutoring.global.ResponseEntity;
import com.example.tutoring.models.Member;
import com.example.tutoring.models.enums.MemberRole;
import com.example.tutoring.models.enums.MemberRole.Authority;
import com.example.tutoring.security.UserDetailsImpl;
import com.example.tutoring.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  @Secured(Authority.ADMIN)
  public List<Member> getMembersByMemberRole(@RequestParam MemberRole memberRole){
    return memberService.getMembersByMemberRole(memberRole);
  }

  @PostMapping("login/success")
  public ResponseEntity<String> loginSuccess(){
    return ResponseEntity.success("로그인성공");
  }


  @GetMapping("/username")
  public String getLoggedInUsername(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    return userDetails.getUsername();
  }

}
