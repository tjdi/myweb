package com.example.repository;

import com.example.domain.Student;

import java.util.List;


public interface StudentRepository {
    List<Student> findAll();

    List<Student> findByTeacherID(Integer id);

    void save(Student student);

    void update(Student student);

    void select(Student student);
}
