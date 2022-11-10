package com.prakash.TodoList.repository;

import com.prakash.TodoList.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepo extends JpaRepository<TodoList, Integer> {
}
