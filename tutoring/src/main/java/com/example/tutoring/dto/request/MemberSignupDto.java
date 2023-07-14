package com.example.tutoring.dto.request;

import com.example.tutoring.models.enums.MemberRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignupDto {
  //아이디
  private String username;
  //비밀번호
  private String password;
  // 관리자, 학생 , 튜터 권한 설정
  private MemberRole memberRole;

}
