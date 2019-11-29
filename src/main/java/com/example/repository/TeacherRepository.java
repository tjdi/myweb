package com.example.repository;

import com.example.domain.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherRepository {
    @Select("select * from teachers")
    List<Teacher> findAll();

    @Insert("insert into teachers(name) values (#{name})")
    int save(Teacher teacher);
}
