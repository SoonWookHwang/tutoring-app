package com.example.tutoring.controller;

import static com.example.tutoring.global.ResponseEntity.fail;
import static com.example.tutoring.global.ResponseEntity.success;

import com.example.tutoring.dto.request.PurchaseDto;
import com.example.tutoring.global.ResponseEntity;
import com.example.tutoring.models.Member;
import com.example.tutoring.models.enums.MemberRole.Authority;
import com.example.tutoring.security.UserDetailsImpl;
import com.example.tutoring.service.SubscriptionMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")
public class SubscriptionMemberController {

  private final SubscriptionMemberService subscriptionMemberService;

  @PostMapping
  @Secured(Authority.STUDENT)
  public ResponseEntity<?> purchaseSubscription(
      @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PurchaseDto dto) {
    try {
      Member user = userDetails.getUser();
      return success(subscriptionMemberService.purchaseSubscription(user, dto));
    } catch (Exception e) {
      return fail(e);
    }
  }

}
