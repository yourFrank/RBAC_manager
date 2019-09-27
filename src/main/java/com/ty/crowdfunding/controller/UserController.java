package com.ty.crowdfunding.controller;

import com.github.pagehelper.PageInfo;
import com.ty.crowdfunding.bean.AjaxResult;
import com.ty.crowdfunding.bean.User;
import com.ty.crowdfunding.service.UserService;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 用户后台管理
 * @Author 71042
 * @Date 2019/9/21 7:43
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(){
        return "user/index";
    }

    @RequestMapping("/toAddPage")
    public String toAddPage(){
        return "user/add";
    }
    @RequestMapping("/toEditPage")
    public String toEditPage(String id, Model model){
        User user=userService.selectUserById(id);
        model.addAttribute("user",user);
        return "user/edit";
    }

    @ResponseBody
    @RequestMapping("/editUser")
    public Object toEditPage(User user){
        AjaxResult ajaxResult=new AjaxResult();
        try {
            userService.updateUser(user);
            ajaxResult.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;

    }


    @ResponseBody
    @RequestMapping("/addUser")
    public Object addUser(User user){
        //设置默认密码
        user.setPassword("123456");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setCreateDate(simpleDateFormat.format(new Date()));
        AjaxResult ajaxResult=new AjaxResult();
        try {
            userService.addUser(user);
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
            PageInfo pageInfo = userService.getPageNo(pageNo, pageSize,queryText);
            ajaxResult.setSuccess(true);
            ajaxResult.setData(pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
            return ajaxResult;


    }
    @ResponseBody
    @RequestMapping("/deleteUser")
    public Object deleteUser(Integer id){
        AjaxResult ajaxResult=new AjaxResult();
        try {
            userService.deleteUser(id);
            ajaxResult.setSuccess(true);
        }
        catch (Exception e){
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;

    }

    @ResponseBody
    @RequestMapping("/deleteUsers")
    public Object deleteUser(Integer[] userIds){
        AjaxResult ajaxResult=new AjaxResult();
        try {
            userService.deleteUsers(userIds);
            ajaxResult.setSuccess(true);
        }
        catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }
        return ajaxResult;

    }


}

