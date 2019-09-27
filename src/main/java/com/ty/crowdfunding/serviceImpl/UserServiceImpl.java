package com.ty.crowdfunding.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.crowdfunding.bean.User;
import com.ty.crowdfunding.dao.UserDao;
import com.ty.crowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author 71042
 * @Date 2019/9/21 7:50
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public PageInfo getPageNo(Integer pageNo, Integer pageSize,String queryText) {
        PageHelper.startPage(pageNo,pageSize);
       List<User> users= userDao.getAllUser(queryText) ;
       PageInfo pageInfo=new PageInfo(users,5);
       return pageInfo;
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public User selectUserById(String id) {

        return userDao.selectUserById(id);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void deleteUser(Integer id) {
        userDao.deleteUser(id);
    }

    public void deleteUsers(Integer[] userIds) {
        userDao.deleteUsers(userIds);
    }
}
