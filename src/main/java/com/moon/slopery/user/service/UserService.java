package com.moon.slopery.user.service;

import com.moon.slopery.user.UserRepository;
import com.moon.slopery.user.dto.UserSignupRequestDto;
import com.moon.slopery.user.entity.User;
import com.moon.slopery.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(UserSignupRequestDto requestDto) {

        Optional<User> checkUserId = userRepository.findById(requestDto.getUserId());
        String userId = requestDto.getUserId();
        if (checkUserId.isPresent()) {
            throw new IllegalArgumentException("이미 등록된 아이디 입니다");
        }

        Optional<User> checkEmail = userRepository.findByEmail(requestDto.getEmail());
        String email = requestDto.getEmail();
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("이미 등록된 이메일 입니다.");
        }

        Optional<User> checkPhoneNumber = userRepository.findByPhoneNumber(requestDto.getPhoneNumber());
        if (checkPhoneNumber.isPresent()) {
            throw new IllegalArgumentException("이미 등록된 전화번호 입니다.");
        }

        String password = passwordEncoder.encode(requestDto.getPassword());

        UserRoleEnum role = UserRoleEnum.USER;

        User user = new User(userId, email, password, requestDto.getUserName(), requestDto.getPhoneNumber(), role);

        userRepository.save(user);
    }
}
