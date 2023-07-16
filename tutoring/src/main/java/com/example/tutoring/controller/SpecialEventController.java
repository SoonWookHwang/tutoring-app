package com.example.tutoring.controller;

import com.example.tutoring.dto.request.SpecialEventRegisterDto;
import com.example.tutoring.models.enums.MemberRole.Authority;
import com.example.tutoring.service.SpecialEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class SpecialEventController {
  private final SpecialEventService specialEventService;


  @Secured(Authority.ADMIN)
  @PostMapping("/admin")
  public Long SpecialEventRegister(@RequestBody SpecialEventRegisterDto dto){
    return specialEventService.createEvent(dto);
  }

}
