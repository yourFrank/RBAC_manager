package com.ty.crowdfunding.dao;

import com.ty.crowdfunding.bean.Role;
import com.ty.crowdfunding.bean.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.rmi.server.UID;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author 71042
 * @Date 2019/9/22 13:08
 */
public interface RoleDao {

   

    void addRole(Role role);

    @Select("select * from role where id=#{id}")
    Role selectRoleById(String id);

    void updateRole(Role role);

    @Delete("delete from role where id=#{id}")
    void deleteRole(Integer id);


    void deleteRoles(@Param("roleIds") Integer[] roleIds);

    List<Role> getPageNo(String queryText);

    @Select("select * from role ")
    List<Role> getAllRole();


    List<Integer> getassignedRoleId(Integer uid);

    void assignedRole(@Param("uid")Integer uid, @Param("role_id")Integer[] role_id);

    void unassignedRole(@Param("uid")Integer uid, @Param("role_id")Integer[] role_id);
}
