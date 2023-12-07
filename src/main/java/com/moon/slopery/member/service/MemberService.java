package com.moon.slopery.member.service;

import com.moon.slopery.CommonResponseDto;
import com.moon.slopery.member.repository.MemberRepository;
import com.moon.slopery.member.dto.SignupRequestDto;
import com.moon.slopery.member.entity.Member;
import com.moon.slopery.member.entity.MemberRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<CommonResponseDto> signup(SignupRequestDto requestDto) {

        Optional<Member> checkUserId = memberRepository.findById(requestDto.getUserId());
        String userId = requestDto.getUserId();
        if (checkUserId.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDto("이미 등록된 아이디 입니다", HttpStatus.BAD_REQUEST.value()));
        }

        Optional<Member> checkEmail = memberRepository.findByEmail(requestDto.getEmail());
        String email = requestDto.getEmail();
        if (checkEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDto("이미 등록된 이메일 입니다.", HttpStatus.BAD_REQUEST.value()));
        }

        Optional<Member> checkPhoneNumber = memberRepository.findByPhoneNumber(requestDto.getPhoneNumber());
        if (checkPhoneNumber.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponseDto("이미 등록된 전화번호 입니다.", HttpStatus.BAD_REQUEST.value()));
        }

        String password = passwordEncoder.encode(requestDto.getPassword());

        MemberRoleEnum role = MemberRoleEnum.MEMBER;

        Member member = new Member(userId, email, password, requestDto.getUserName(), requestDto.getPhoneNumber(), role);
        memberRepository.save(member);

        return ResponseEntity.status(HttpStatus.CREATED).body(new CommonResponseDto("회원 가입 완료", HttpStatus.CREATED.value()));
    }
}
