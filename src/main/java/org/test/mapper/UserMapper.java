package org.test.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.test.entity.UserEntity;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user limit #{page},#{pageSize}")
    public List<UserEntity> select(@Param("page") int page, @Param("pageSize") int pageSize);

    @Select("select * from user where id in(select id from (select id from user limit #{page},#{pageSize}) as t)")
    public List<UserEntity> select2(@Param("page") int page, @Param("pageSize") int pageSize);

    @Select("select count(*) from user")
    public int size();

    @Insert("insert into user(username , password) values (#{username} , #{password})")
    public void insert(@Param("username") String username, @Param("password") String password);
}
