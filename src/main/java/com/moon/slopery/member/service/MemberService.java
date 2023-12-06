package com.moon.slopery.member.service;

import com.moon.slopery.member.repository.MemberRepository;
import com.moon.slopery.member.dto.SignupRequestDto;
import com.moon.slopery.member.entity.Member;
import com.moon.slopery.member.entity.MemberRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequestDto requestDto) {

        Optional<Member> checkUserId = memberRepository.findById(requestDto.getUserId());
        String userId = requestDto.getUserId();
        if (checkUserId.isPresent()) {
            throw new IllegalArgumentException("이미 등록된 아이디 입니다");
        }

        Optional<Member> checkEmail = memberRepository.findByEmail(requestDto.getEmail());
        String email = requestDto.getEmail();
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("이미 등록된 이메일 입니다.");
        }

        Optional<Member> checkPhoneNumber = memberRepository.findByPhoneNumber(requestDto.getPhoneNumber());
        if (checkPhoneNumber.isPresent()) {
            throw new IllegalArgumentException("이미 등록된 전화번호 입니다.");
        }

        String password = passwordEncoder.encode(requestDto.getPassword());

        MemberRoleEnum role = MemberRoleEnum.MEMBER;

        Member member = new Member(userId, email, password, requestDto.getUserName(), requestDto.getPhoneNumber(), role);

        memberRepository.save(member);
    }
}
