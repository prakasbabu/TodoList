package com.prakash.TodoList.service;

import com.prakash.TodoList.Dto.TodoListDto;
import com.prakash.TodoList.entity.TodoList;
import com.prakash.TodoList.repository.TodoListRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TodoListServiceImpl implements TodoListService{
    @Autowired
    private TodoListRepo todoListRepo;
    @Autowired
    private ModelMapper modelMapper;

    public TodoListDto entityToDto(TodoList entity){
        TodoListDto dto = new TodoListDto();
        BeanUtils.copyProperties(entity,dto);
       return dto;
    }

    public TodoList dtoToEntity(TodoListDto dto){
        TodoList entity = new TodoList();
        BeanUtils.copyProperties(dto,entity);
        return entity;
    }



    @Override
    public List<TodoListDto> findAll() {

       List<TodoList> todos = this.todoListRepo.findAll();
        List<TodoListDto> todoDtos = todos.stream()
        		.map(todoList -> this.modelMapper.map(todoList,TodoListDto.class))
                .collect(Collectors.toList());

        return todoDtos;
    }

    @Override
    public TodoListDto findById(Integer id) {
        return null;
    }


    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public TodoListDto create(TodoListDto todoListDto) {
        TodoList todoList = this.dtoToEntity(todoListDto);
        TodoList createdtodoList = todoListRepo.save(todoList);
        return this.entityToDto(createdtodoList);
    }
}
