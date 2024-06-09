package com.myapps.spring_security_jwt.controller;

import com.myapps.spring_security_jwt.model.ToDo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ToDoController {
    @GetMapping("/getToDos")
    public List<ToDo> getToDo(){
        List<ToDo> todo = new ArrayList<ToDo>();
        todo.add(new ToDo(1, "To Do 1"));
        todo.add(new ToDo(2, "To Do 2"));
        todo.add(new ToDo(3, "To Do 3"));
        todo.add(new ToDo(4, "To Do 4"));
        todo.add(new ToDo(5, "To Do 5"));
        return todo;
    }
}
