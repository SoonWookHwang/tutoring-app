package com.example.tutoring.controller;



import com.example.tutoring.dto.request.SubsRegisterDto;
import com.example.tutoring.models.enums.MemberRole;
import com.example.tutoring.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriptions")
public class SubscriptionController {
  private final SubscriptionService subscriptionService;

  @Secured(MemberRole.Authority.ADMIN)
  @PostMapping("/admin")
  public Long registerSubscription(@RequestBody SubsRegisterDto dto){
    return subscriptionService.registerSubscription(dto);
  }

}
