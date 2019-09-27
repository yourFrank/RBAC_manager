package com.ty.crowdfunding.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ty.crowdfunding.bean.Role;
import com.ty.crowdfunding.bean.Role;
import com.ty.crowdfunding.dao.RoleDao;
import com.ty.crowdfunding.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author 71042
 * @Date 2019/9/22 12:40
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;


    public PageInfo getPageNo(Integer pageNo, Integer pageSize,String queryText) {
        PageHelper.startPage(pageNo,pageSize);
        List<Role> Roles= roleDao.getPageNo(queryText) ;
        PageInfo pageInfo=new PageInfo(Roles,5);
        return pageInfo;
    }

    public void addRole(Role Role) {
        roleDao.addRole(Role);
    }



    public Role selectRoleById(String id) {

        return roleDao.selectRoleById(id);
    }

    public void updateRole(Role Role) {
        roleDao.updateRole(Role);
    }

    public void deleteRole(Integer id) {
        roleDao.deleteRole(id);
    }

    public void deleteRoles(Integer[] RoleIds) {
        roleDao.deleteRoles(RoleIds);
    }

    public List<Role> getAllRole() {
        return roleDao.getAllRole();
    }

    public List<Integer> getassignedRoleId(Integer uid) {
        return roleDao.getassignedRoleId(uid);
    }

    public void assignedRole(Integer uid, Integer[] role_id) {
        roleDao.assignedRole(uid,role_id);
    }

    public void unassignedRole(Integer uid, Integer[] role_id) {
        roleDao.unassignedRole(uid,role_id);

    }
}
