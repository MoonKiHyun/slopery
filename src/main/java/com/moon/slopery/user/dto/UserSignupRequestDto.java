package com.moon.slopery.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequestDto {

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
