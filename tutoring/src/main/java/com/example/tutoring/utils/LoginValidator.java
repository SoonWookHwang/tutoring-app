package com.example.tutoring.utils;

import com.example.tutoring.models.Member;
import com.example.tutoring.security.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoginValidator {

  public static boolean isLogin(Member member) {

    UserDetailsImpl authenticatedUser = (UserDetailsImpl) SecurityContextHolder.getContext()
        .getAuthentication().getPrincipal();

    return member.getUsername().equals(authenticatedUser.getUsername());
  }

}
