package com.zxz.service;



import com.zxz.po.Comment;
import com.zxz.po.Zan;
import com.zxz.util.Result;

import java.util.List;

/**
 * Created by limi on 2017/10/22.
 */
public interface CommentService {
    public Result insertComment(Integer article,Integer response_id,String comment_content);

    public Result insertReply(Integer article,Integer parent_id,Integer answere_id,Integer response_id,String comment_content);

    public Result selectComment(Integer article_id);

    public List<Comment> selectReply(Integer comment_id);

    public Integer select_is_zan( Integer user_id,Integer comment);

    public Result add_zan(Zan zan);

    public Result delete_zan(Integer user_id,Integer comment);





}
