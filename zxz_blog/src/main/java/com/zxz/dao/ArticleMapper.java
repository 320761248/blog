package com.zxz.dao;

import com.sun.deploy.panel.IProperty;
import com.zxz.po.Article;
import com.zxz.po.User;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface ArticleMapper {


    @Select("SELECT a.id,u.`user_name`,a.look_number,a.article_author,a.`last_updateDate`,a.article_label,a.`article_category`,a.pulish_date,a.article_title,a.zan_number,a.comment_number,c.category_name,l.label_name\n" +
            "                    FROM article a,category c ,label l,`user` u \n" +
            "                       WHERE l.id=a.`article_label` AND c.`id`=a.`article_category` AND a.`article_author`=u.`user_id` ORDER BY a.`pulish_date` DESC")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "look_number",column = "look_number"),
            @Result(property = "zan_number",column = "zan_number"),
            @Result(property = "pulish_date",column = "pulish_date"),
            @Result(property = "article_title",column = "article_title"),
            @Result(property = "comment_number",column = "comment_number"),
            @Result(property = "last_updateDate",column = "last_updateDate"),
            @Result(property = "article_author",column = "article_author",
                    one=@One(select ="com.zxz.dao.UserMapper.selectUserById" )
            ),
            @Result(property = "article_category",column = "article_category",
                    one=@One(select ="com.zxz.dao.CategoryMapper.selectCategoryById" )
            ),
            @Result(property = "article_label",column = "article_label",
                    one=@One(select = "com.zxz.dao.LabelMapper.selectLabelById")
            )
    })
    public List<Article> selectArticleListAsTime();


    @Select("SELECT a.`id`,a.`article_title`,a.`article_author`,a.`article_content`,a.`look_number`,a.`article_category`,a.`look_number`,\n" +
            "a.`zan_number`,a.`comment_number`,a.`pulish_date`,a.`last_updateDate`,u.`user_name`,c.`category_name`\n" +
            " FROM article  a,category  c,`user` u WHERE a.`article_author`=u.`user_id` AND a.`article_category`=c.`id` AND a.id=#{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "look_number",column = "look_number"),
            @Result(property = "article_content",column = "article_content"),
            @Result(property = "zan_number",column = "zan_number"),
            @Result(property = "pulish_date",column = "pulish_date"),
            @Result(property = "article_title",column = "article_title"),
            @Result(property = "comment_number",column = "comment_number"),
            @Result(property = "last_updateDate",column = "last_updateDate"),
            @Result(property = "article_author",column = "article_author",
                    one=@One(select ="com.zxz.dao.UserMapper.selectUserById" )
            ),
            @Result(property = "article_category",column = "article_category",
                    one=@One(select ="com.zxz.dao.CategoryMapper.selectCategoryById" )
            )
    })
    public Article selectArticleById(Integer id);



    @Select("SELECT a.id,a.look_number,a.`last_updateDate`,a.article_label,a.`article_category`,a.pulish_date,a.article_title,a.zan_number,a.comment_number,c.category_name,l.label_name\n" +
            "                    FROM article a,category c ,label l\n" +
            "                       WHERE l.id=a.`article_label` AND c.`id`=a.`article_category` and a.article_category=#{category_id} ORDER BY a.`pulish_date` DESC")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "look_number",column = "look_number"),
            @Result(property = "zan_number",column = "zan_number"),
            @Result(property = "pulish_date",column = "pulish_date"),
            @Result(property = "article_title",column = "article_title"),
            @Result(property = "comment_number",column = "comment_number"),
            @Result(property = "last_updateDate",column = "last_updateDate"),
            @Result(property = "article_category",column = "article_category",
                    one=@One(select ="com.zxz.dao.CategoryMapper.selectCategoryById" )
            ),
            @Result(property = "article_label",column = "article_label",
                    one=@One(select = "com.zxz.dao.LabelMapper.selectLabelById")
            )
    })
    public List<Article> selectArticleByCategoryId(@Param("category_id") Integer category_id);


    @Insert("insert into article(article_content,article_author,article_category,article_label,pulish_date,last_updateDate,article_title) " +
        "values(#{article_content},#{user_id},#{article_category},#{article_label},#{publish_data},#{last_update_date},#{article_title})")
    public int insertArticle(Integer article_label, Integer article_category, String article_content, String article_title,String publish_data,String last_update_date,Integer user_id);

    @Select("select count(id) from article where article_category=#{id}")
    public int selectArticleNumByCategoryId(Integer id);

    @Select("SELECT id,article_title FROM article WHERE id<#{cur_id} ORDER BY id DESC LIMIT 0,1")
    public Article selectLastId(int cur_id);

    @Select("SELECT id,article_title FROM article WHERE id>#{cur_id} ORDER BY id ASC LIMIT 0,1")
    public Article selectNextId(int cur_id);











}
