package com.zxz.po;

import java.util.Date;

public class Comment_zan_record {
    private Integer id;
    private Article article;
    private User zan;
    private Date zan_time;
    private Comment comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getZan() {
        return zan;
    }

    public void setZan(User zan) {
        this.zan = zan;
    }

    public Date getZan_time() {
        return zan_time;
    }

    public void setZan_time(Date zan_time) {
        this.zan_time = zan_time;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
