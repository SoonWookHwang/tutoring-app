package com.example.tutoring.repository;

import com.example.tutoring.models.SubscriptionMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionMemberRepository extends JpaRepository<SubscriptionMember,Long> {

}
