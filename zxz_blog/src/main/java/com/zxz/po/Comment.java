package com.zxz.po;


import java.util.Date;

/**
 * Created by limi on 2017/10/14.
 */

public class Comment {

    private Integer id;
    private Integer article;
    private Integer parent_id;
    private User answerer;
    private User responser;
    private String comment_date;
    private Integer zan_num;
    private String comment_content;

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

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public User getAnswerer() {
        return answerer;
    }

    public void setAnswerer(User answerer) {
        this.answerer = answerer;
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
}
