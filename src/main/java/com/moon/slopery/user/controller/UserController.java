package com.moon.slopery.user.controller;

import com.moon.slopery.user.CommonResponseDto;
import com.moon.slopery.user.dto.UserSignupRequestDto;
import com.moon.slopery.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(@Valid @RequestBody UserSignupRequestDto requestDto) {
        userService.signup(requestDto);

        return ResponseEntity.status(201).body(new CommonResponseDto("회원 가입 완료", HttpStatus.CREATED.value()));
    }
}
