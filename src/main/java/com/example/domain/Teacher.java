package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher implements Serializable {
    private int id;
    private String name;

    public Teacher(String name) {
        this.name=name;
    }
}
