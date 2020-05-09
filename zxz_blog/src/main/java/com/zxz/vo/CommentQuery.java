package com.zxz.vo;

import com.zxz.po.Article;
import com.zxz.po.Comment;
import com.zxz.po.User;

import java.util.List;

public class CommentQuery {
    private Integer id;
    private Integer article;


    private User responser;
    private String comment_date;
    private Integer zan_num;

    private String comment_content;
    List<Comment> replys;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticle() {
        return article;
    }

    public void setArticle(Integer article) {
        this.article = article;
    }



    public User getResponser() {
        return responser;
    }

    public void setResponser(User responser) {
        this.responser = responser;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public Integer getZan_num() {
        return zan_num;
    }

    public void setZan_num(Integer zan_num) {
        this.zan_num = zan_num;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public List<Comment> getReplys() {
        return replys;
    }

    public void setReplys(List<Comment> replys) {
        this.replys = replys;
    }
}
