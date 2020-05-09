package com.zxz.web;


import com.github.pagehelper.PageHelper;
import com.zxz.po.Article;
import com.zxz.po.Category;
import com.zxz.service.ArticleService;
import com.zxz.service.CategoryService;
import com.zxz.service.LabelService;
import com.zxz.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LabelService labelService;

    @RequestMapping("/index")
    public String index(Model model){


        return "admin_index";
    }

    @RequestMapping("article_manage")
    public String article_manage(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize, Model model){
        //查询model
        PageHelper.startPage(pageNo,pageSize);
        Result article_result=articleService.selectArticleListAsTime(pageNo,pageSize);
        model.addAttribute("article_result",article_result);
        return "article_manage";
    }
    @RequestMapping("label_manage")
    public String label_manage(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize, Model model){
        PageHelper.startPage(pageNo,pageSize);
        Result label_result=labelService.selectAllLabel();
        model.addAttribute("label_result",label_result);
        return "label_manage";
    }
    @RequestMapping("category_manage")
    public String category_manage(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize, Model model){
        PageHelper.startPage(pageNo,pageSize);
        Result category_result=categoryService.selectAllCategory();
        model.addAttribute("category_result",category_result);
        return "category_manage";
    }



}
