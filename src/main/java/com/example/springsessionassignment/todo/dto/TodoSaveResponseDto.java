package com.example.springsessionassignment.todo.dto;

import lombok.Getter;

@Getter
public class TodoSaveResponseDto {

    private Long id;
    private String content;

    public TodoSaveResponseDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
