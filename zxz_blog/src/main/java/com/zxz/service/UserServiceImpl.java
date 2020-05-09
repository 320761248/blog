package com.zxz.service;


import com.zxz.dao.UserMapper;
import com.zxz.po.User;
import com.zxz.util.CodeMsg;
import com.zxz.util.MD5Utils;
import com.zxz.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by limi on 2017/10/15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public Result addUser(String email, String password) {
        String new_password=MD5Utils.code(email,password);
        String username="用户"+email;
        System.out.println(username);
        int res=userMapper.addUser(username,email,new_password);
        if(res==1){
            return Result.success(CodeMsg.ADD_USER_SUCCESS);
        }
        return Result.error(CodeMsg.ADD_USER_FAIL);

    }
}
