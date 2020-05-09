package com.zxz.service;


import com.zxz.dao.CommentMapper;
import com.zxz.po.Comment;
import com.zxz.po.User;
import com.zxz.po.Zan;
import com.zxz.util.CodeMsg;
import com.zxz.util.Result;
import com.zxz.vo.CommentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by limi on 2017/10/22.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    //插入一级评论
    @Override
    public Result insertReply(Integer article,Integer parent_id,Integer answere_id,Integer response_id,String comment_content) {
        long currentTimeMillis = System.currentTimeMillis();
        Date date =new java.sql.Date(currentTimeMillis);
        Date time=new Time(currentTimeMillis);
        String datetime = date.toString() + " " + time.toString();
        Integer zan_num=0;
        User responser=new User();
        User answerer=new User();
        responser.setUser_id(response_id);
        answerer.setUser_id(answere_id);
        Comment comment=new Comment();
        comment.setParent_id(parent_id);
        comment.setArticle(article);
        comment.setAnswerer(answerer);
        comment.setResponser(responser);
        comment.setZan_num(zan_num);
        comment.setComment_date(datetime);
        comment.setComment_content(comment_content);


        int result=commentMapper.insertReply(comment);

        //查询到插入的这条记录
        Comment return_comment=commentMapper.selectCommentById(comment.getId());

        if(result>0){

            return Result.success(return_comment);
        }else{
            return Result.error(CodeMsg.INSERT_COMMENT_ERROR);
        }


    }

    @Override
    public Result insertComment(Integer article,Integer response_id,String comment_content) {
        long currentTimeMillis = System.currentTimeMillis();
        Date date =new java.sql.Date(currentTimeMillis);
        Date time=new Time(currentTimeMillis);
        String datetime = date.toString() + " " + time.toString();
        int zan_num=0;

        Comment comment=new Comment();
        comment.setArticle(article);
        User responser=new User();
        responser.setUser_id(response_id);
        comment.setResponser(responser);
        comment.setComment_date(datetime);
        comment.setZan_num(zan_num);
        comment.setComment_content(comment_content);
        int result=commentMapper.insertComment(comment);



        //获取插入的这条评论

        Comment return_comment=commentMapper.selectCommentById(comment.getId());
        if(result>0){
            return Result.success(return_comment);
        }else{
            return Result.error(CodeMsg.INSERT_COMMENT_SUCCESS);
        }


    }

    @Override
    public Result selectComment(Integer article_id) {
        //查询一级评论
        List<CommentQuery>  commentQueries=new ArrayList<>();
        List<Comment> comments=commentMapper.selectComment(article_id);
        CommentQuery commentQuery;
        List<Comment> replys=null;
        for(Comment comment:comments){

            commentQuery=new CommentQuery();
            replys=commentMapper.selectReply(comment.getId());
            commentQuery.setId(comment.getId());
            commentQuery.setArticle(comment.getArticle());
            commentQuery.setResponser(comment.getResponser());
            commentQuery.setZan_num(comment.getZan_num());
            commentQuery.setComment_date(comment.getComment_date());
            commentQuery.setComment_content(comment.getComment_content());
            commentQuery.setReplys(replys);

            commentQueries.add(commentQuery);


        }
        if(comments.size()>0){
            return Result.success(commentQueries);
        }
        return Result.error(CodeMsg.LOAD_COMMENT_EMPTY);

    }

    @Override
    public List<Comment> selectReply(Integer comment_id) {
        List<Comment> comments=commentMapper.selectReply(comment_id);
        return comments;
    }

    @Override
    public Integer select_is_zan(Integer user_id, Integer comment) {
        Integer num=commentMapper.select_is_zan(user_id,comment);

        return num;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Result add_zan(Zan zan) {
        long currentTimeMillis = System.currentTimeMillis();
        Date date =new java.sql.Date(currentTimeMillis);
        Date time=new Time(currentTimeMillis);
        String datetime = date.toString() + " " + time.toString();
        zan.setZan_time(datetime);
        Integer res=commentMapper.add_zan(zan);

        int update_res=commentMapper.addZanNum(zan.getComment());
        if(res>0&&update_res>0){
            return Result.success();
        }
        return Result.error(CodeMsg.ZAN_ERROR);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Result delete_zan(Integer user_id, Integer comment) {
        Integer res=commentMapper.delete_zan(user_id,comment);
        Integer update_res=commentMapper.deleteZanNum(comment);
        if(res>0&&update_res>0){
            return Result.success();
        }
        return Result.error(CodeMsg.DELETE_ZAN_ERROR);
    }
}
