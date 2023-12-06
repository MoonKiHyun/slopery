package com.moon.slopery.member.repository;

import com.moon.slopery.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByPhoneNumber(String phoneNumber);
}
