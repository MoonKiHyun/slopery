package com.moon.slopery.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moon.slopery.member.dto.LoginRequestDto;
import com.moon.slopery.jwt.JwtUtil;
import com.moon.slopery.CommonResponseDto;
import com.moon.slopery.member.entity.MemberRoleEnum;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/member/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getUserId(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        MemberRoleEnum role = ((UserDetailsImpl) authResult.getPrincipal()).getMember().getRole();

        String token = jwtUtil.createToken(username, role);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);

        CommonResponseDto commonResponseDto = new CommonResponseDto("로그인 성공", HttpStatus.OK.value());
        commonResponse(response, commonResponseDto);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        CommonResponseDto commonResponseDto = new CommonResponseDto("로그인 실패", HttpStatus.UNAUTHORIZED.value());
        commonResponse(response, commonResponseDto);
    }

    private void commonResponse(HttpServletResponse response, CommonResponseDto commonResponseDto) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(commonResponseDto);

        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(jsonResponse);
    }
}

