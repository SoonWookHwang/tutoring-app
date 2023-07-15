package com.example.tutoring.repository;

import com.example.tutoring.models.Member;
import com.example.tutoring.models.enums.MemberRole;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
  Optional<Member> findByUsername(String username);

  List<Member> findAllByRole(MemberRole memberRole);

}
