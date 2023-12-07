package com.moon.slopery.post.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {

    @NotNull
    private String title;

    @NotNull
    private String content;
}
