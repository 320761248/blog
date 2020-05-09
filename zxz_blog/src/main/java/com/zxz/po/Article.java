package com.zxz.po;


import java.util.Date;


/**
 * Created by zxz in 2020/4/15
 */

public class Article {


    private Long id;
    private String article_content;
    private Integer look_number;

    private User article_author;


    private String article_title;
    private String pulish_date;
    private Label article_label;
    private Category article_category;
    private String last_updateDate;
    private Integer zan_number;
    private Integer comment_number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public Integer getLook_number() {
        return look_number;
    }

    public void setLook_number(Integer look_number) {
        this.look_number = look_number;
    }

    public User getArticle_author() {
        return article_author;
    }

    public void setArticle_author(User article_author) {
        this.article_author = article_author;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getPulish_date() {
        return pulish_date;
    }

    public void setPulish_date(String pulish_date) {
        this.pulish_date = pulish_date;
    }

    public Label getArticle_label() {
        return article_label;
    }

    public void setArticle_label(Label article_label) {
        this.article_label = article_label;
    }

    public Category getArticle_category() {
        return article_category;
    }

    public void setArticle_category(Category article_category) {
        this.article_category = article_category;
    }

    public String getLast_updateDate() {
        return last_updateDate;
    }

    public void setLast_updateDate(String last_updateDate) {
        this.last_updateDate = last_updateDate;
    }

    public Integer getZan_number() {
        return zan_number;
    }

    public void setZan_number(Integer zan_number) {
        this.zan_number = zan_number;
    }

    public Integer getComment_number() {
        return comment_number;
    }

    public void setComment_number(Integer comment_number) {
        this.comment_number = comment_number;
    }
}
