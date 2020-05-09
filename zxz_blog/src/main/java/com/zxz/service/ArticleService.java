package com.zxz.service;


import com.zxz.po.Article;
import com.zxz.util.Result;

import java.util.List;
import java.util.Map;

/**
 * Created by zxz on 2017/10/20.
 */
public interface ArticleService {
    public  Result selectArticleById(Integer id);

    public Result selectArticleListAsTime(int pageNo,int pageSize);

    public Result insertArticle(Integer article_label,Integer article_category,String article_content,String article_title);


    public Result selectArticleListByCategoryAsTime(int pageNo,int pageSize,Integer category_id);
//    public Result updateLastArticle(Integer category_id);

    public Map<String,Article> selectLastNextId(Integer cur_id);





}
