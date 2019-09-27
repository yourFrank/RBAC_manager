package com.ty.crowdfunding.serviceImpl;

import com.ty.crowdfunding.bean.Permission;
import com.ty.crowdfunding.bean.User;
import com.ty.crowdfunding.dao.PermissionDao;
import com.ty.crowdfunding.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author 71042
 * @Date 2019/9/23 15:54
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    public List<Permission> getPermissionChildren(Integer id) {
        return permissionDao.getPermissionChildren(id);
    }

    public List<Permission> getAllPermissions() {
        return permissionDao.getAllPermissions();
    }

    public void addPermission(Permission permission) {
        permissionDao.addPermission(permission);
    }

    public Permission getPermissionById(Integer id) {
        return permissionDao.getPermissionById(id);
    }

    public void editPermission(Permission permission) {
         permissionDao.editPermission(permission);
    }

    public void deletePermission(Integer id) {
        permissionDao.deletePermission(id);
    }

    public void assignPermission(Integer role_id, Integer[] permissionIds) {
        //分配角色前清空之前该角色的权限
        permissionDao.deleteExistPermission(role_id);
        permissionDao.assignPermission(role_id,permissionIds);
    }

    public List<Integer> loadAssignedPermissions(Integer role_id) {
        return permissionDao.loadAssignedPermissions(role_id);
    }

    public List<Permission> getPermissionByUser(User user) {
        return permissionDao.getPermissionByUser(user);
    }
}
