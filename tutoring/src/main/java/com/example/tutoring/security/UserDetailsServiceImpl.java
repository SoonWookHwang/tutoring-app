package com.example.tutoring.security;

import com.example.tutoring.models.Member;
import com.example.tutoring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final MemberRepository memberRepository;

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Member user = memberRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다"));

    return new UserDetailsImpl(user);
  }
}

