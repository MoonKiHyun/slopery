package com.moon.slopery.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @NotNull
    private String userId;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String userName;

    @NotNull
    private String phoneNumber;
}
