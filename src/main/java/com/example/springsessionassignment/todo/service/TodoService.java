package com.example.springsessionassignment.todo.service;

import com.example.springsessionassignment.todo.dto.*;
import com.example.springsessionassignment.todo.entity.Todo;
import com.example.springsessionassignment.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    //  Create
    @Transactional
    public TodoSaveResponseDto save(TodoSaveReqeustDto dto) {
        Todo todo = new Todo(dto.getContent());
        Todo savedTodo = todoRepository.save(todo);

        return new TodoSaveResponseDto(
                savedTodo.getId(),
                savedTodo.getContent()
        );
    }

    //  Read
    @Transactional(readOnly = true)
    public List<TodoResponseDto> findAll() {
        List<Todo> todos = todoRepository.findAll();

        return todos.stream().map(
                todo -> new TodoResponseDto(
                        todo.getId(),
                        todo.getContent()
                )).toList();
    }

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
    public TodoUpdateResponseDto update(Long todoId, TodoUpdateRequestDto dto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("Todo not found")
        );
        todo.update(dto.getContent());

        return new TodoUpdateResponseDto(
                todo.getId(),
                todo.getContent()
        );
    }

    //  Delete
    @Transactional
    public void deleteById(Long todoId) {
        if (!todoRepository.existsById(todoId)) {
            throw new IllegalStateException("Todo not found");
        }
        todoRepository.deleteById(todoId);
    }
}
