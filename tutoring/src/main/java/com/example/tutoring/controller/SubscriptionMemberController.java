package com.example.tutoring.controller;

import static com.example.tutoring.global.ResponseEntity.fail;
import static com.example.tutoring.global.ResponseEntity.success;

import com.example.tutoring.dto.request.PurchaseDto;
import com.example.tutoring.global.ResponseEntity;
import com.example.tutoring.service.SubscriptionMemberService;
import lombok.RequiredArgsConstructor;
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
  public ResponseEntity<?> purchaseSubscription(@RequestBody PurchaseDto dto){
    try {
      return success(subscriptionMemberService.purchaseSubscription(dto));
    }catch (Exception e){
      return fail(e);
    }
  }

}
