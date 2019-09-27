package com.ty.crowdfunding.dao;

import com.ty.crowdfunding.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description TODO
 * @Author 71042
 * @Date 2019/9/21 7:50
 */

public interface UserDao {
    List<User> getAllUser(String queryText);

    void addUser(User user);

    @Select("select * from user where id=#{id}")
    User selectUserById(String id);

    void updateUser(User user);

    @Delete("delete from user where id=#{id}")
    void deleteUser(Integer id);

    void deleteUsers(Integer[] userIds);
}
