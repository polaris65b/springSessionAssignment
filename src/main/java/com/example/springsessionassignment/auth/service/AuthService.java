package com.example.springsessionassignment.auth.service;

import com.example.springsessionassignment.auth.dto.AuthLoginRequestDto;
import com.example.springsessionassignment.auth.dto.AuthLoginResponseDto;
import com.example.springsessionassignment.auth.dto.AuthSignupRequestDto;
import com.example.springsessionassignment.member.entity.Member;
import com.example.springsessionassignment.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signup(AuthSignupRequestDto dto) {
        Member member = new Member(dto.getEmail());
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public AuthLoginResponseDto login(AuthLoginRequestDto dto) {
        Member member = memberRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("Member not found")
        );
        return new AuthLoginResponseDto(member.getId());
    }
}
