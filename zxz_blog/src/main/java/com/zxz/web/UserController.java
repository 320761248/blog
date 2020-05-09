package com.zxz.web;


import com.zxz.service.UserService;
import com.zxz.util.CodeMsg;
import com.zxz.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by limi on 2017/10/15.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private Long start_time;    //验证码发送时间

    private int ver_code;    //验证码
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     */
    @RequestMapping("/regist")
    @ResponseBody
    public Result addUser(@RequestParam(value = "email") String email,@RequestParam(value = "password") String password){
        Long end_time = System.currentTimeMillis();
        if(end_time-start_time>60*1000){
            Result.error(CodeMsg.VERFICATION_TIMEOUT);
        }
        Result result=userService.addUser(email,password);

        return result;
    }
    @RequestMapping("/sendVericationCode")
    @ResponseBody
    public Result sendVericationCode(@RequestParam(value="email") String email){
        int random=(int)(Math.random()*9000+1000);
        start_time = System.currentTimeMillis();
        String ver_code="1234";

        return Result.success(ver_code);
    }

    /**
     * 用户查询
     */
    public int selectAllUser(){
        return 1;
    }

    /**
     * 更新用户信息
     */
    public int updateUser(){
        return 1;
    }




}
