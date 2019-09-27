package com.ty.crowdfunding.controller;

import com.ty.crowdfunding.bean.AjaxResult;
import com.ty.crowdfunding.bean.Permission;
import com.ty.crowdfunding.service.PermissionService;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author 71042
 * @Date 2019/9/23 15:16
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/index")
    public String index(){
        return "permission/index";
    }

    @RequestMapping("/toAssignPermissionPage")
    public String toAssignPermissionPage(){
        return "role/assignPermission";
    }

    @ResponseBody
    @RequestMapping("/assignPermission")
    public Object assignPermission(Integer role_id,Integer[] permissionIds ){
        AjaxResult ajaxResult=new AjaxResult();
        try {
            permissionService.assignPermission( role_id,permissionIds );
            ajaxResult.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ajaxResult;
    }



    @RequestMapping("/toAddPage")
    public String toAddPage(){
        return "permission/add";
    }
    @RequestMapping("/toEditPage")
    public String toEditPage(Integer id, Model model){
        Permission permission=permissionService.getPermissionById(id);
        model.addAttribute("permission",permission);
        return "permission/edit";
    }

    @RequestMapping("/deletePermission")
    @ResponseBody
    public Object deletePermission(Integer id){
        AjaxResult ajaxResult=new AjaxResult();
        try {
            permissionService.deletePermission(id);
            ajaxResult.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ajaxResult;
    }
    @RequestMapping("/editPermission")
    @ResponseBody
    public Object editPermission(Permission permission){
        AjaxResult ajaxResult=new AjaxResult();
        try {
            permissionService.editPermission(permission);
            ajaxResult.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ajaxResult;
    }
    @RequestMapping("/addPermission")
    @ResponseBody
    public Object addPermission(Permission permission){
        AjaxResult ajaxResult=new AjaxResult();
       try {
           permissionService.addPermission(permission);
           ajaxResult.setSuccess(true);
       }catch (Exception e){
           e.printStackTrace();
       }
       return ajaxResult;
    }


    /*
     * @Author frank
     * @Description //回显已经分配权限的数据
     * @Date 2019/9/24
     * @Param
     * @return java.lang.Object
     **/
    @ResponseBody
    @RequestMapping("/loadAssignedData")
    public Object loadAssignedData(Integer role_id){

        List<Permission> finalPermissions=new ArrayList<Permission>();
        //查询所有的权限
        List<Permission> permissions=permissionService.getAllPermissions();
        //查找该角色已经分配的权限
        List<Integer> permissionIds=permissionService.loadAssignedPermissions(role_id);

        Map<Integer,Permission> permissionMap=new HashMap<Integer, Permission>();
        for (Permission permission : permissions) {
            permissionMap.put(permission.getId(),permission);
        }
        for (Permission permission : permissions) {
            //如果该权限已经分配给该角色，将该权限选中属性设置为true
            if (permissionIds.contains(permission.getId())){
                permission.setChecked(true);
            }
            //            如果当前节点为父节点
            if (permission.getPid()==0){
                finalPermissions.add(permission);
            }else {
//                给父节点设置子节点
                Permission p = permissionMap.get(permission.getPid());
                p.getChildren().add(permission);
            }

        }
        return finalPermissions;
    }
    @ResponseBody
    @RequestMapping("/loadData")
    public Object loadData(){
        /*
        Permission parent=new Permission();
        parent.setId(0);
        setPermissionData(parent);
        return parent.getChildren();

         */
//        for循环List同样性能不好
        List<Permission> finalPermissions=new ArrayList<Permission>();
        List<Permission> permissions=permissionService.getAllPermissions();
        Map<Integer,Permission> permissionMap=new HashMap<Integer, Permission>();
        for (Permission permission : permissions) {
           permissionMap.put(permission.getId(),permission);
        }
        for (Permission permission : permissions) {
//            如果当前节点为父节点
            if (permission.getPid()==0){
                finalPermissions.add(permission);
            }else {
//                给父节点设置子节点
                Permission p = permissionMap.get(permission.getPid());
                p.getChildren().add(permission);
            }

        }
        return finalPermissions;
    }



    /*
     * @Author frank
     * @Description //递归给每一个节点生成数据,性能不好需要多次访问数据库
     * @Date 2019/9/23
     * @Param
     * @param parent
     * @return void
     **/
    /*
    public void setPermissionData(Permission parent){
        List<Permission> permissionChildren = permissionService.getPermissionChildren(parent.getId());
        for (Permission permissionChild : permissionChildren) {
            setPermissionData(permissionChild);
        }
        parent.setChildren(permissionChildren);
    }

     */
}
