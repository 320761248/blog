package com.zxz.dao;


import com.zxz.po.Comment;
import com.zxz.po.Zan;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

//    @Insert("INSERT INTO `comment`(article,response_id,comment_date,zan_num,comment_content) VALUES(#{article},#{response_id},#{comment_date},#{zan_num},#{comment_content})")
//    public int insertComment(Integer article,Integer response_id,String comment_date,Integer zan_num,String comment_content);


    @Insert("INSERT INTO `comment`(article,response_id,comment_date,zan_num,comment_content) VALUES(#{article},${responser.user_id},#{comment_date},#{zan_num},#{comment_content})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public int insertComment(Comment comment);

//    @Insert("INSERT INTO `comment`(article,parent_id,answere_id,response_id,comment_date,zan_num,comment_content) VALUES(#{article},#{parent_id},#{answere_id},#{response_id},#{comment_date},#{zan_num},#{comment_content})")
//    public int insertReply(Integer article,Integer parent_id,Integer answere_id,Integer response_id,String comment_date,Integer zan_num,String comment_content);


    @Insert("INSERT INTO `comment`(article,parent_id,answere_id,response_id,comment_date,zan_num,comment_content) VALUES(#{article},#{parent_id},${answerer.user_id},${responser.user_id},#{comment_date},#{zan_num},#{comment_content})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public int insertReply(Comment comment);

    @Select("SELECT u.user_name,u.sex,u.user_head,c.id,c.article,c.response_id,c.comment_date,c.zan_num,c.comment_content FROM `comment` c, `user` u WHERE c.response_id=u.`user_id`  AND ( c.parent_id IS NULL) and c.article=#{article_id} ORDER BY c.`zan_num` DESC ,c.id DESC")

    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "article",column = "article"),
            @Result(property = "responser",column = "response_id",one=@One(select ="com.zxz.dao.UserMapper.selectUserById" )),
            @Result(property = "comment_date",column = "comment_date"),
            @Result(property = "zan_num",column = "zan_num"),
            @Result(property = "comment_content",column = "comment_content"),
    })
    public List<Comment> selectComment(Integer article_id);

    @Select("SELECT c.*,u.user_name,u.sex,u.user_head FROM `comment` c,`user` u WHERE c.`response_id`=u.user_id AND c.`answere_id`=u.user_id AND c.`parent_id`=#{comment_id} ORDER BY c.zan_num DESC,  c.id DESC")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "article",column = "article"),
            @Result(property = "parent_id",column = "parent_id"),
            @Result(property = "answerer",column = "answere_id",one=@One(select ="com.zxz.dao.UserMapper.selectUserById" )),
            @Result(property = "responser",column = "response_id" ,one=@One(select ="com.zxz.dao.UserMapper.selectUserById" )),
            @Result(property = "comment_date",column = "comment_date"),
            @Result(property = "zan_num",column = "zan_num"),
            @Result(property = "comment_content",column = "comment_content"),
    })
    public List<Comment> selectReply(Integer comment_id);


    @Select("SELECT COUNT(*) FROM comment_zan_record c WHERE c.`zan_user`=#{user_id} AND c.`comment`=#{comment}")
    public Integer select_is_zan( Integer user_id,Integer comment);

    @Insert("INSERT INTO comment_zan_record(zan_user,zan_time,COMMENT) VALUES(#{zan_user},#{zan_time},#{comment})")
    public Integer add_zan(Zan zan);
    @Delete("DELETE FROM comment_zan_record WHERE zan_user=#{user_id} AND COMMENT=#{comment}")
    public Integer delete_zan(Integer user_id,Integer comment);

    @Select("select * from comment where id=#{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "article",column = "article"),
            @Result(property = "parent_id",column = "parent_id"),
            @Result(property = "answerer",column = "answere_id",one=@One(select ="com.zxz.dao.UserMapper.selectUserById" )),
            @Result(property = "responser",column = "response_id" ,one=@One(select ="com.zxz.dao.UserMapper.selectUserById" )),
            @Result(property = "comment_date",column = "comment_date"),
            @Result(property = "zan_num",column = "zan_num"),
            @Result(property = "comment_content",column = "comment_content"),
    })
    public Comment selectCommentById(Integer id);

    @Update("update comment set zan_num=zan_num+1 where  id=#{id}")
    public int addZanNum(Integer id);

    @Update("update comment set zan_num=zan_num-1 where  id=#{id}")
    public int deleteZanNum(Integer id);
}
