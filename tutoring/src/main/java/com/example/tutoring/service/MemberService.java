package com.example.tutoring.service;

import com.example.tutoring.dto.request.MemberSignupDto;
import com.example.tutoring.models.Member;
import com.example.tutoring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  //회원가입
  public Long signup(MemberSignupDto dto) {
    Member newMember = Member.builder()
        .username(dto.getUsername())
        .password(dto.getPassword())
        .role(dto.getMemberRole())
        .build();
    return memberRepository.save(newMember).getId();
  }

}
