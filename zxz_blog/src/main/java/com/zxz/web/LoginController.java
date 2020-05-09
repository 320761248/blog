package com.zxz.web;


import com.zxz.po.User;
import com.zxz.service.UserService;
import com.zxz.util.CodeMsg;
import com.zxz.util.MD5Utils;
import com.zxz.util.Result;
import com.zxz.util.ShiroUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by limi on 2017/10/15.
 */
@Controller

public class LoginController {


    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public String   userLogin (){
        return "login";
    }

    @RequestMapping("/userLogin")
    @ResponseBody
    public Result  userLogin (
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "passWord") String passWord, HttpSession session){

        String new_password="";

        try{

            new_password=MD5Utils.code(userName,passWord);
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, new_password);
            subject.login(token);
            User user=(User) subject.getPrincipal();
            session.setAttribute("user",user);
        }catch (Exception e) {
            e.printStackTrace();

            return Result.error(CodeMsg.USER_LOGIN_ERROR);

        }
        return Result.success();
    }

    /**
     * 发送验证码到邮箱
     * @return
     */



    @RequestMapping("/userLogOut")
    public String logout (){
        ShiroUtils.logout();
        return "success" ;
    }





}
