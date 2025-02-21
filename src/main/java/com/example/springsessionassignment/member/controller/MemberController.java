package com.example.springsessionassignment.member.controller;

import com.example.springsessionassignment.member.dto.MemberResponseDto;
import com.example.springsessionassignment.member.dto.MemberSaveRequestDto;
import com.example.springsessionassignment.member.dto.MemberSaveResponseDto;
import com.example.springsessionassignment.member.dto.MemberUpdateRequestDto;
import com.example.springsessionassignment.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    //  Create
    @PostMapping("/members")
    public ResponseEntity<MemberSaveResponseDto> save(@RequestBody MemberSaveRequestDto dto) {
        return ResponseEntity.ok(memberService.save(dto));
    }

    //  Reade
    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> getAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberResponseDto> getOne(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.findById(memberId));
    }

    //  Update
    @PatchMapping("/members/{memberId}")
    public void update(@PathVariable Long memberId, @RequestBody MemberUpdateRequestDto dto) {
        memberService.update(memberId,dto);
    }

    //  Delete
    @DeleteMapping("/members/{memberId}")
    public void delete(@PathVariable Long memberId) {
        memberService.delete(memberId);
    }
}
