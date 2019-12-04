package com.example.cache;

import com.example.repository.StudentRepository;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class Level2CacheTest {

    private SqlSessionFactory sqlSessionFactory;
    //表示数据库连接，类似于JDBC中的Connection
    private SqlSession sqlSession;

    @Before //用这个注解标记的方法会在所有的test执行前被优先执行
    public void before() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test(){
        sqlSession = sqlSessionFactory.openSession();
        StudentRepository studentRepository = sqlSession.getMapper(StudentRepository.class);
    }

    @After
    public void after() {
        sqlSession.close();
    }
}
