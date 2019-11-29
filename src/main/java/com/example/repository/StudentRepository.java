package com.example.repository;

import com.example.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface StudentRepository {
    List<Student> findAll();

    List<Student> findByTeacherID(Integer id);

    int save(Student student);

    int update(Student student);

    int delete(Integer id);

    List<Student> findByNameAndTeacherId(
            @Param("name") String name,
            @Param("id") Integer id
    );
}
