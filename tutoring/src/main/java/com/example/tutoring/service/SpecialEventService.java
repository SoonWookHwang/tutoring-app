package com.example.tutoring.service;

import com.example.tutoring.dto.request.SpecialEventRegisterDto;
import com.example.tutoring.models.SpecialEvent;
import com.example.tutoring.repository.SpecialEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialEventService {
  private final SpecialEventRepository specialEventRepository;

  public Long createEvent(SpecialEventRegisterDto dto){
    SpecialEvent newSpecialEvent = SpecialEvent.builder()
        .title(dto.getTitle())
        .discount(dto.getDiscount())
        .build();
    return specialEventRepository.save(newSpecialEvent).getId();
  }

}
