package com.moon.slopery.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private String userId;
    private String password;
}
