package com.zxz.po;

import java.util.Date;

public class Article_zan_record {
    private Integer id;
    private Article article;
    private User zan_user;
    private Integer is_read;
    private Date zan_time;

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

    public User getZan_user() {
        return zan_user;
    }

    public void setZan_user(User zan_user) {
        this.zan_user = zan_user;
    }

    public Integer getIs_read() {
        return is_read;
    }

    public void setIs_read(Integer is_read) {
        this.is_read = is_read;
    }

    public Date getZan_time() {
        return zan_time;
    }

    public void setZan_time(Date zan_time) {
        this.zan_time = zan_time;
    }
}
