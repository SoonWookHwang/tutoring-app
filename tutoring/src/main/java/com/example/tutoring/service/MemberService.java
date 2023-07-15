package com.example.tutoring.service;

import com.example.tutoring.dto.request.MemberSignupDto;
import com.example.tutoring.models.Member;
import com.example.tutoring.models.enums.MemberRole;
import com.example.tutoring.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  //회원가입
  @Transactional
  public Long signup(MemberSignupDto dto) {
    Member newMember = Member.builder()
        .username(dto.getUsername())
        .password(dto.getPassword())
        .role(dto.getMemberRole())
        .build();
    return memberRepository.save(newMember).getId();
  }

  public List<Member> getMembersByMemberRole(MemberRole memberRole){
    return memberRepository.findAllByRole(memberRole);
  }

}
