package com.prakash.TodoList.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoListDto {
    private int id;

    private String date;

    private boolean reminder;
    private String text;

}
