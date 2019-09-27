package com.ty.crowdfunding.service;

import com.github.pagehelper.PageInfo;
import com.ty.crowdfunding.bean.User;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author 71042
 * @Date 2019/9/21 7:50
 */
public interface UserService {
    PageInfo getPageNo(Integer pageNo, Integer pageSize,String queryText);

    void addUser(User user);

    User selectUserById(String id);

    void updateUser(User user);

    void deleteUser(Integer id);

    void deleteUsers(Integer[] userIds);
}
