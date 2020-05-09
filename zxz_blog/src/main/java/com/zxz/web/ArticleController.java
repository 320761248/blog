package com.zxz.web;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zxz.po.Article;
import com.zxz.po.Label;
import com.zxz.service.ArticleService;
import com.zxz.service.CategoryService;
import com.zxz.service.LabelService;
import com.zxz.util.Result;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.plugin2.main.client.MozillaServiceDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文章信息的控制器
 */
@Controller
public class ArticleController {




    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LabelService labelService;

    @RequestMapping("/article_write")
    public String article_write(Model model){
        Result category_res=categoryService.selectAllCategory();
        Result label_res=labelService.selectAllLabel();
        model.addAttribute("category_res",category_res);
        model.addAttribute("label_res",label_res);


        //查询标签和分类


        return "article_write";
    }
    //
    @RequestMapping("publish_article")
    @ResponseBody
    public Result publish_article(@RequestParam String article_label,@RequestParam String article_category ,@RequestParam String article_content,String article_title ){
        //查询当前分类的最新文章，更新最新文章，并且查询得到最新的作为这次的上一次


        Result result=articleService.insertArticle(Integer.parseInt(article_label),Integer.parseInt(article_category),article_content,article_title);

        return result;
    }

    @RequestMapping("/uploadImage")
    @ResponseBody
    public JSONObject uploadImage(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response){

        String filePath="F://workspace/blog_file/blog_img";


        String fileContentType = file.getContentType();
        String fileExtension = fileContentType.substring(fileContentType.indexOf("/") + 1);
        long time= new Date().getTime()/1000;
        String fileName = time + "." + fileExtension;



        File targetFile = new File(filePath, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }


        JSONObject res = new JSONObject();
        //注意：一定要加http
        res.put("url","http://127.0.0.1:8080/blog_img/"+fileName);
        res.put("success", 1);
        res.put("message", "upload success!");

        return res;
    }



    @RequestMapping("selectArticleNumByCategoryId")
    @ResponseBody
    public Result selectArticleNumByCategoryId(@RequestParam int pageNo, @RequestParam int pageSize,@RequestParam String category_id){
        Result result=articleService.selectArticleListByCategoryAsTime(pageNo,pageSize,Integer.parseInt(category_id));
        return result;
    }


    @RequestMapping("/article/{id}")
    public String  article(@PathVariable Integer id,Model model){
        Result article_result=articleService.selectArticleById(id);

        //查询评论
        model.addAttribute("article_result",article_result);
        return "article";
    }

    //加载上一篇下一篇
    @RequestMapping("loadlastAndNext")
    @ResponseBody
    public Map<String,Article>  loadlastAndNext(@RequestParam String cur_id){
       Map map=articleService.selectLastNextId(Integer.parseInt(cur_id));
       return map;
    }





}
