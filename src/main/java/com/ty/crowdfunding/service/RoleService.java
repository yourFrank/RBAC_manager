package com.ty.crowdfunding.service;

import com.github.pagehelper.PageInfo;
import com.ty.crowdfunding.bean.Role;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author 71042
 * @Date 2019/9/22 12:39
 */

public interface RoleService {


    Role selectRoleById(String id);

    void updateRole(Role role);

    void addRole(Role role);

    PageInfo getPageNo(Integer pageNo, Integer pageSize, String queryText);

    void deleteRole(Integer id);

    void deleteRoles(Integer[] roleIds);

    List<Role> getAllRole();

    List<Integer> getassignedRoleId(Integer uid);

    void assignedRole(Integer uid, Integer[] role_id);

    void unassignedRole(Integer uid, Integer[] role_id);
}
