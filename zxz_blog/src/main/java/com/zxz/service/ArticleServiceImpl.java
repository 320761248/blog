package com.zxz.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxz.dao.ArticleMapper;
import com.zxz.po.Article;
import com.zxz.po.User;
import com.zxz.util.CodeMsg;
import com.zxz.util.Result;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;

/**
 * Created by limi on 2017/10/20.
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public Result selectArticleById(Integer id) {
        Article article=articleMapper.selectArticleById(id);
        if(article==null){
            return Result.error(CodeMsg.LOAD_ARTICLE_ERROR);
        }

        return Result.success(article);
    }
    /**
     * 分页查询主页的article并且按时间排序
     * @return
     */
    @Override
    public Result selectArticleListAsTime(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Article> articles=articleMapper.selectArticleListAsTime();
        PageInfo<Article> pageInfo = new PageInfo<Article>(articles);



        if(articles.size()==0){
            return Result.error(CodeMsg.ARTICLE_QUERY_EMPTY);
        }
        return Result.success(pageInfo);
    }




    public Result selectArticleNumByCategoryId(int pageNo,int pageSize,int category_id){
        PageHelper.startPage(pageNo,pageSize);
        List<Article> articles=articleMapper.selectArticleByCategoryId(category_id);
        PageInfo<Article> pageInfo = new PageInfo<Article>(articles);
        return Result.success(pageInfo);

    }




    @Override
    public Result insertArticle(Integer article_label, Integer article_category, String article_content, String article_title) {
            long currentTimeMillis = System.currentTimeMillis();
            Date date =new java.sql.Date(currentTimeMillis);
            Date time=new Time(currentTimeMillis);

            String datetime = date.toString() + " " + time.toString();
            int res=articleMapper.insertArticle(article_label,article_category,article_content,article_title,datetime,datetime,1);
            if(res==0){
                return Result.success(CodeMsg.PUBLISH_ARTICLE_ERROR);
            }
            return Result.success(CodeMsg.PUBLISH_ARTICLE_SUCCESS);

    }



    @Override
    public Result selectArticleListByCategoryAsTime(int pageNo, int pageSize, Integer category_id) {
        PageHelper.startPage(pageNo,pageSize);
        List<Article> articles=articleMapper.selectArticleByCategoryId(category_id);
        PageInfo<Article> pageInfo = new PageInfo<Article>(articles);
        if(articles.size()==0){
            return Result.error(CodeMsg.ARTICLE_QUERY_EMPTY);
        }
        return Result.success(pageInfo);
    }

    @Override
    public Map<String,Article> selectLastNextId(Integer cur_id) {
        Article lastArticle=articleMapper.selectLastId(cur_id);
        Article nextArticle=articleMapper.selectNextId(cur_id);
        Map<String,Article> map=new HashMap<>();
        if(lastArticle==null){
            map.put("lastArticle",null);
        }else{
            map.put("lastArticle",lastArticle);
        }
        if(nextArticle==null){
            map.put("nextArticle",null);
        }else{
            map.put("nextArticle",nextArticle);
        }

        return map;
    }


//    @Override
//    public Result updateLastArticle(Integer category_id) {
//        int res=articleMapper.updateLastArticle(category_id);
//        if(res==0){
//            return Result.success(CodeMsg.LINK_CUR_ARTICLE);
//        }
//        return Result.success();
//    }


}
