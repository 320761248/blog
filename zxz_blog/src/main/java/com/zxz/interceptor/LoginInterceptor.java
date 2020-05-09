package com.zxz.interceptor;

import com.zxz.util.CodeMsg;
import com.zxz.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by limi on 2017/10/15.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {



        HttpSession session = request.getSession();

        Object user = session.getAttribute("user");
        PrintWriter out = null;
        if (user == null) {
            try {
                System.out.println("拦截用户");
                JSONObject res = new JSONObject();
                res.put("code", 3);
                res.put("msg", "请先登陆");
                out = response.getWriter();
                out = response.getWriter();
                out.append(res.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;

        }
        return true;

    }


}
