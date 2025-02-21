package com.example.springsessionassignment.todo.controller;

import com.example.springsessionassignment.common.consts.Const;
import com.example.springsessionassignment.todo.dto.*;
import com.example.springsessionassignment.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    //  create
    @PostMapping("/todos")
    public ResponseEntity<TodoSaveResponseDto> save(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @RequestBody TodoSaveReqeustDto dto
    ) {
        return ResponseEntity.ok(todoService.save(memberId, dto));
    }

    //  read all
    @GetMapping("/todos")
    public ResponseEntity<List<TodoResponseDto>> getAll() {
        return ResponseEntity.ok(todoService.findAll());
    }
    // read one
    @GetMapping("/todos/{todoId}")
    public ResponseEntity<TodoResponseDto> getOne(@PathVariable Long todoId) {
        return ResponseEntity.ok(todoService.findById(todoId));
    }

    //  update
    @PatchMapping("/todos/{todoId}")
    public ResponseEntity<TodoUpdateResponseDto> update(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long todoId,
            @RequestBody TodoUpdateRequestDto dto
    ) {
        return ResponseEntity.ok(todoService.update(memberId, todoId, dto));
    }

    //  delete
    @DeleteMapping("/todos/{todoId}")
    public void delete(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long todoId
    ) {
        todoService.deleteById(memberId, todoId);
    }
}
