package com.ty.crowdfunding.dao;

import com.ty.crowdfunding.bean.Permission;
import com.ty.crowdfunding.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description TODO
 * @Author 71042
 * @Date 2019/9/23 15:54
 */

public interface PermissionDao {
    @Select("select * from permission where pid=#{id}")
    List<Permission> getPermissionChildren(Integer id);

    @Select("select * from permission")
    List<Permission> getAllPermissions();

    @Insert("insert into permission(name,url,pid) values(#{name},#{url},#{pid})")
    void addPermission(Permission permission);

    @Select("select * from permission where id =#{id}")
    Permission getPermissionById(Integer id);

    @Update("update permission set name=#{name},url=#{url},icon=#{icon} where id=#{id}")
    void editPermission(Permission permission);

    @Delete("delete from permission where id =#{id}")
    void deletePermission(Integer id);

    void assignPermission(@Param("role_id") Integer role_id,@Param("permissionIds") Integer[] permissionIds);

    @Select("select p_id from role_permission where r_id =#{role_id}")
    List<Integer> loadAssignedPermissions(Integer role_id);

    @Delete("delete from role_permission where r_id=#{role_id}")
    void deleteExistPermission(Integer role_id);


    List<Permission> getPermissionByUser(User user);
}
