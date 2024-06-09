package com.myapps.spring_security_jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ToDo {
    private int id;
    private String title;
}
