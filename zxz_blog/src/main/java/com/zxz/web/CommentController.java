package com.zxz.web;

import com.zxz.po.Comment;
import com.zxz.po.User;
import com.zxz.po.Zan;
import com.zxz.service.ArticleService;
import com.zxz.service.CommentService;
import com.zxz.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by limi on 2017/10/22.
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;



    @RequestMapping("loadComment")
    @ResponseBody
    public Result loadComment(@RequestParam Integer article_id, HttpServletRequest request){

        Result result=commentService.selectComment(article_id);
        return result;

    }


    @RequestMapping("insertComment")
    @ResponseBody
    public Result insertComment(@RequestParam Integer article,  @RequestParam String comment_content, HttpServletRequest request){
        User responser=(User) request.getSession().getAttribute("user");


        Result result=commentService.insertComment(article,responser.getUser_id(),comment_content);
        return result;

    }

    @RequestMapping("insertReply")
    @ResponseBody
    public Result insertReply(@RequestParam Integer article,@RequestParam Integer parent_id,@RequestParam Integer answere_id,@RequestParam String comment_content,HttpServletRequest request){
        User responser=(User) request.getSession().getAttribute("user");
        Result result=commentService.insertReply(article,parent_id,answere_id,responser.getUser_id(),comment_content);
        return result;

    }

    @RequestMapping("select_is_zan")
    @ResponseBody
    public Result select_is_zan(@RequestParam  Integer comment,HttpServletRequest request){
        User responser=(User) request.getSession().getAttribute("user");
        Integer res=commentService.select_is_zan(responser.getUser_id(),comment);
        return Result.success(res);
    }

    @RequestMapping("add_zan")
    @ResponseBody
    public Result add_zan(@RequestParam   Integer comment,HttpServletRequest request){
        User responser=(User) request.getSession().getAttribute("user");
        Zan zan=new Zan();
        zan.setComment(comment);
        zan.setZan_user(responser.getUser_id());
        return commentService.add_zan(zan);
    }
    @RequestMapping("delete_zan")
    @ResponseBody
    public Result delete_zan(Integer comment,HttpServletRequest request){
        User responser=(User) request.getSession().getAttribute("user");
        return commentService.delete_zan(responser.getUser_id(),comment);
    }





}
