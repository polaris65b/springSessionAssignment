package com.example.springsessionassignment.todo.dto;

import lombok.Getter;

@Getter
public class TodoResponseDto {

    private Long id;
    private String content;

    public TodoResponseDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
