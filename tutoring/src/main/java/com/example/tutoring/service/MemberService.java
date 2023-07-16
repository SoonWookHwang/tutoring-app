package com.example.tutoring.service;

import com.example.tutoring.dto.request.MemberSignupDto;
import com.example.tutoring.models.Member;
import com.example.tutoring.models.enums.MemberRole;
import com.example.tutoring.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  //회원가입
  @Transactional
  public Long signup(MemberSignupDto dto) {
    Optional<Member> found = memberRepository.findByUsername(dto.getUsername());
    if (found.isPresent()) {
      throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
    }
    String password = passwordEncoder.encode(dto.getPassword());
    Member newMember = Member.builder()
        .username(dto.getUsername())
        .password(password)
        .role(dto.getMemberRole())
        .build();
    return memberRepository.save(newMember).getId();
  }

  public List<Member> getMembersByMemberRole(MemberRole memberRole){
    return memberRepository.findAllByRole(memberRole);
  }

}
