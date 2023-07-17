package com.example.tutoring.models;

import com.example.tutoring.models.enums.MemberRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Timestamped{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="member_id")
  private Long id;

  @Column(unique = true,nullable = false,updatable = false)
  private String username;

  @Column(nullable = false)
  @JsonIgnore
  private String password;
  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private MemberRole role;

  @OneToMany(mappedBy = "purchasedStudent")
  @JsonIgnore
  private List<SubscriptionMember> subscriptionMembers;

  @Builder
  protected Member(String username, String password, MemberRole role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }
  public void updateMember(String username, String password,MemberRole role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }
}

