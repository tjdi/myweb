package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher implements Serializable {
    private Integer id;
    private String name;
    private Set<Student> students = new HashSet<>();
    //List
    //在一个实体中带有另一个实体的集合，数据库是完全没有反馈的 {不变}

    public Teacher(String name) {
        this.name = name;
    }
}
