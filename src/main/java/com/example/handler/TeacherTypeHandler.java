package com.example.handler;

import com.example.domain.Teacher;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.INTEGER)
public class TeacherTypeHandler extends BaseTypeHandler<Teacher> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Teacher teacher, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, teacher.getId());
    }

    @Override
    public Teacher getNullableResult(ResultSet resultSet, String s) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getInt(s));
        return teacher;
    }

    @Override
    public Teacher getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getInt(i));
        return teacher;
    }

    @Override
    public Teacher getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(callableStatement.getInt(i));
        return teacher;
    }
}
