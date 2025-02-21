package com.example.springsessionassignment.todo.service;

import com.example.springsessionassignment.member.entity.Member;
import com.example.springsessionassignment.member.repository.MemberRepository;
import com.example.springsessionassignment.todo.dto.*;
import com.example.springsessionassignment.todo.entity.Todo;
import com.example.springsessionassignment.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    //  Create
    @Transactional
    public TodoSaveResponseDto save(Long memberId, TodoSaveReqeustDto dto) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                ()-> new RuntimeException("Member not found")
        );
        Todo todo = new Todo(
                dto.getContent(),
                member
        );
        Todo savedTodo = todoRepository.save(todo);

        return new TodoSaveResponseDto(
                savedTodo.getId(),
                savedTodo.getContent(),
                member.getId(),
                member.getEmail()
        );
    }

    //  Read All
    @Transactional(readOnly = true)
    public List<TodoResponseDto> findAll() {
        List<Todo> todos = todoRepository.findAll();

        return todos.stream().map(
                todo -> new TodoResponseDto(
                        todo.getId(),
                        todo.getContent()
                )).toList();
    }
    // Read One
    @Transactional(readOnly = true)
    public TodoResponseDto findById(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("Todo not found")
        );

        return new TodoResponseDto(
                todo.getId(),
                todo.getContent()
        );
    }

    //  Update
    @Transactional
    public TodoUpdateResponseDto  update(
            Long memberId,
            Long todoId,
            TodoUpdateRequestDto dto
    ) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new RuntimeException("Member not found")
        );
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("Todo not found")
        );
        if (!todo.getMember().getId().equals(member.getId())) {
            throw new IllegalStateException("This Todo belongs to another member.");
        }
        todo.update(dto.getContent());

        return new TodoUpdateResponseDto(
                todo.getId(),
                todo.getContent()
        );
    }

    //  Delete
    @Transactional
    public void deleteById(Long memberId, Long todoId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("Member not found")
        );

        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalStateException("Todo not found")
        );

        if (!todo.getMember().getId().equals(member.getId())) {
           throw new IllegalStateException("This Todo belongs to another member.");
        }

        todoRepository.deleteById(todoId);
    }
}
