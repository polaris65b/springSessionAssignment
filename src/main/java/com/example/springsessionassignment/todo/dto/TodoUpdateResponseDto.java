package com.example.springsessionassignment.todo.dto;

import lombok.Getter;

@Getter
public class TodoUpdateResponseDto {

    private Long id;
    private String content;

    public TodoUpdateResponseDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
