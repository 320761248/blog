package com.zxz.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxz.po.Article;
import com.zxz.po.Category;
import com.zxz.po.Label;
import com.zxz.service.ArticleService;
import com.zxz.service.LabelService;
import com.zxz.service.CategoryService;
import com.zxz.util.Result;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * index页面控制器
 */
@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LabelService labelService;

    /**
     *
     * @function 页面加载
     * @具体 加载按时间排序的博客list,分类list，标签的list，播放量排序的前10个article
     * @return
     */




    @RequestMapping("index")
    public String index() {


//
//        Result article_result=articleService.selectArticleListAsTime(pageNo,pageSize);
//
//
//        Result category_result=categoryService.selectCategoryAsNumber();
//
//
//        Result label_result=labelService.selectLabelAsNumber();
//
//
//
//        model.addAttribute("article_result",article_result);
//        model.addAttribute("category_result",category_result);
//        model.addAttribute("label_result",label_result);


        return "index";
    }



    //加载博客
    @RequestMapping("loadArticle")
    @ResponseBody
    public Result loadArticle(@RequestParam int pageNo, @RequestParam int pageSize){
        Result result=articleService.selectArticleListAsTime(pageNo,pageSize);
        return result;

    }


    //加载分类   只显示7个
    @RequestMapping("loadCategory")
    @ResponseBody
    public Result loadCategory(){
        Result result=categoryService.selectCategoryAsNumber();
        return result;

    }




    //加载分类   只显示10个
    @RequestMapping("loadLabel")
    @ResponseBody
    public Result loadLabel(){
        Result result=labelService.selectLabelAsNumber();
        return result;

    }




}
