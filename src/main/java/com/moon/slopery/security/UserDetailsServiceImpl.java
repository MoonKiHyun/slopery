package com.moon.slopery.security;

import com.moon.slopery.member.repository.MemberRepository;
import com.moon.slopery.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = memberRepository.findById(userId).orElseThrow(() ->
                new NullPointerException("존재하지 않는 userId 입니다."));

        return new UserDetailsImpl(member);
    }
}