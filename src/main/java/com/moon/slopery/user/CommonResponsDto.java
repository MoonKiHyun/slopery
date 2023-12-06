package com.moon.slopery.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponsDto {

    private final String msg;
    private final int status;

    public CommonResponsDto(String msg, int status) {
        this.msg = msg;
        this.status = status;
    }
}
