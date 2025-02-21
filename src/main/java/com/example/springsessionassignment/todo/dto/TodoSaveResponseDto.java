package com.example.springsessionassignment.todo.dto;

import lombok.Getter;

@Getter
public class TodoSaveResponseDto {

    private final Long id;
    private final String content;
    private final Long memberId;
    private final String email;

    public TodoSaveResponseDto(Long id, String content, Long memberId, String email) {
        this.id = id;
        this.content = content;
        this.memberId = memberId;
        this.email = email;
    }
}
