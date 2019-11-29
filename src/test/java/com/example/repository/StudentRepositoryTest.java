package com.example.repository;

import com.example.domain.Sex;
import com.example.domain.Student;
import com.example.domain.Teacher;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class StudentRepositoryTest {


    private SqlSessionFactory sqlSessionFactory;
    //表示数据库连接，类似于JDBC中的Connection
    private SqlSession sqlSession;

    @Before //用这个注解标记的方法会在所有的test执行前被优先执行
    public void before() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void select1() {
        sqlSession = sqlSessionFactory.openSession();
        StudentRepository studentRepository = sqlSession.getMapper(StudentRepository.class);
        studentRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void save() {
        sqlSession = sqlSessionFactory.openSession();
        StudentRepository studentRepository = sqlSession.getMapper(StudentRepository.class);

        Teacher teacher = new Teacher();
        teacher.setId(8);

        Student student=new Student();
        student.setName("tom1");
        student.setBirthday(LocalDate.now());
        student.setSex(Sex.M);
        student.setTeacher(teacher);

        studentRepository.save(student);

        sqlSession.commit();
    }

    @Test
    public void update() {
        sqlSession = sqlSessionFactory.openSession();
        StudentRepository studentRepository = sqlSession.getMapper(StudentRepository.class);
        Student student = new Student();
        student.setId(2);
        student.setBirthday(LocalDate.of(2020,1,1));
        student.setName("Tom");
        student.setSex(Sex.F);
        Teacher teacher=new Teacher();
        teacher.setId(1);
        student.setTeacher(teacher);
        studentRepository.update(student);
        sqlSession.commit();
    }

    @After
    public void after() {
        sqlSession.close();
    }
}