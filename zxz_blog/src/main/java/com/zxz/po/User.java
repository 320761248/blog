package com.zxz.po;



/**
 * Created by limi on 2017/10/14.
 */

public class User {

   private Integer user_id;
   private String user_name;
   private String password;
   private String email;
   private Integer sex;

   private Integer status;
    private Integer user_head;


    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUser_head() {
        return user_head;
    }

    public void setUser_head(Integer user_head) {
        this.user_head = user_head;
    }
}
