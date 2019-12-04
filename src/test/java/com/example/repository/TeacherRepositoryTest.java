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

public class TeacherRepositoryTest {

    //代表整个数据库在内存中的镜像，整个项目中应该只有一个，不应该频繁打开和关闭
    private SqlSessionFactory sqlSessionFactory;
    //表示数据库连接，类似于JDBC中的Connection
    private SqlSession sqlSession;

    @Before //用这个注解标记的方法会在所有的test执行前被优先执行
    public void before() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void save() {
        sqlSession = sqlSessionFactory.openSession();   //打开数据库连接
        TeacherRepository teacherRepository = sqlSession.getMapper(TeacherRepository.class);
        //打开连接后，用getMapper方法获得这个TeacherRepository接口的一个实现类（MyBatis通过反射帮助我们生成的）

        teacherRepository.save(new Teacher("Now")); //为Teacher类加一个带String参数的构造器
        sqlSession.commit();//提交事务（增删改)
    }

    @Test
    public void save2(){
        sqlSession = sqlSessionFactory.openSession();   //打开数据库连接
        TeacherRepository teacherRepository = sqlSession.getMapper(TeacherRepository.class);
        teacherRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void find() {
        sqlSession = sqlSessionFactory.openSession();
        TeacherRepository teacherRepository = sqlSession.getMapper(TeacherRepository.class);
        teacherRepository.findAll().forEach(t -> {
            System.out.printf("[%-3d%-10s]\n", t.getId(), t.getName());
            t.getStudents().forEach(s -> System.out.println("\t" + s));
        });
    }

    @After  //用这个注解标记的方法会在所有的test执行后被执行
    public void after(){
        sqlSession.close();
    }
}