package com.ty.crowdfunding.service;

import com.ty.crowdfunding.bean.Permission;
import com.ty.crowdfunding.bean.User;

import java.util.List;

/**
 * @Description TODO
 * @Author 71042
 * @Date 2019/9/23 15:54
 */
public interface PermissionService {
    List<Permission> getPermissionChildren(Integer id);

    List<Permission> getAllPermissions();

    void addPermission(Permission permission);

    Permission getPermissionById(Integer id);

    void editPermission(Permission permission);

    void deletePermission(Integer id);

    void assignPermission(Integer role_id, Integer[] permissionIds);

    List<Integer> loadAssignedPermissions(Integer role_id);

    List<Permission> getPermissionByUser(User user);
}
