package com.ty.crowdfunding.controller;

import com.github.pagehelper.PageInfo;
import com.ty.crowdfunding.bean.AjaxResult;
import com.ty.crowdfunding.bean.Role;
import com.ty.crowdfunding.bean.Role;
import com.ty.crowdfunding.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author 71042
 * @Date 2019/9/22 11:24
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/role")
    public String toRoleIndexPage(Model model){
        return "role/role";
    }

    @RequestMapping("/toAddPage")
    public String toAddPage(){
        return "role/add";
    }
    @RequestMapping("/toEditPage")
    public String toEditPage(String id, Model model){
        Role role=roleService.selectRoleById(id);
        model.addAttribute("role",role);
        return "role/edit";
    }

    @ResponseBody
    @RequestMapping("/editRole")
    public Object editRole(Role role){
        AjaxResult ajaxResult=new AjaxResult();
        try {
            roleService.updateRole(role);
            ajaxResult.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;

    }


    @ResponseBody
    @RequestMapping("/addRole")
    public Object addRole(Role role){

        AjaxResult ajaxResult=new AjaxResult();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        role.setCreateDate(simpleDateFormat.format(new Date()));
        try {
            roleService.addRole(role);
            ajaxResult.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }
    @RequestMapping("/getPageNo")
    @ResponseBody
    public Object getPageNo( String queryText,Integer pageNo, Integer pageSize){
        AjaxResult ajaxResult=new AjaxResult();
        try {

            PageInfo pageInfo = roleService.getPageNo(pageNo, pageSize,queryText);
            ajaxResult.setSuccess(true);
            ajaxResult.setData(pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;


    }
    @ResponseBody
    @RequestMapping("/deleteRole")
    public Object deleteRole(Integer id){
        AjaxResult ajaxResult=new AjaxResult();
        try {
            roleService.deleteRole(id);
            ajaxResult.setSuccess(true);
        }
        catch (Exception e){
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;

    }

    @ResponseBody
    @RequestMapping("/deleteRoles")
    public Object deleteRoles(Integer[] RoleIds){
        AjaxResult ajaxResult=new AjaxResult();
        try {
            roleService.deleteRoles(RoleIds);
            ajaxResult.setSuccess(true);
        }
        catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;

    }

    @RequestMapping("/toAssignPage")
    public String toAssignPage(Integer uid,Model model){
//        查询所有的角色
        List<Role> roles=roleService.getAllRole();
        //查询已经分配角色的id
        List<Integer> assignedRoleId=roleService.getassignedRoleId(uid);
//       已经分配的角色
       List<Role> assignedRole = new ArrayList<Role>();
       //未分配的角色
        List<Role> unassignedRole=new ArrayList<Role>();
        for (Role role : roles) {
            if (assignedRoleId.contains(role.getId())){
                assignedRole.add(role);
            }else{
                unassignedRole.add(role);
            }
        }
        model.addAttribute("uid",uid);
        model.addAttribute("roles",roles);
        model.addAttribute("assignedRole",assignedRole);
        model.addAttribute("unassignedRole",unassignedRole);

        return "role/assignRole";
    }

    @ResponseBody
    @RequestMapping("/unassignedRole")
    public Object unassignedRole(Integer uid,Integer[] assignedList){
        AjaxResult ajaxResult=new AjaxResult();
        try {
            roleService.unassignedRole(uid,assignedList);
            ajaxResult.setSuccess(true);
        }
        catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }

    @ResponseBody
    @RequestMapping("/assignedRole")
    public Object assignedRole(Integer uid,Integer[] unassignedList){
        AjaxResult ajaxResult=new AjaxResult();
        try {
            roleService.assignedRole(uid,unassignedList);
            ajaxResult.setSuccess(true);
        }
        catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;
    }

}
