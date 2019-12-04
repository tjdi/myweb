package com.example.repository;

import com.example.domain.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherRepository {
//    @Select("select * from teachers")
//
    List<Teacher> findAll();

//    @Insert("insert into teachers(name) values (#{name})")
//    使用注解的方式新增用户
    int save(Teacher teacher);
}
