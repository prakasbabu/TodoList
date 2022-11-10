package com.prakash.TodoList.service;

import com.prakash.TodoList.Dto.TodoListDto;

import java.util.List;

public interface TodoListService {

    public List<TodoListDto> findAll();

    public TodoListDto findById(Integer id);

    public void deleteById(Integer id);

    public TodoListDto create (TodoListDto todoListDto);
}
