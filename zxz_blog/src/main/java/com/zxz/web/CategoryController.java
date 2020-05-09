package com.zxz.web;


import com.zxz.po.Category;
import com.zxz.service.CategoryService;
import com.zxz.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * Created by limi on 2017/10/16.
 */

@Controller

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("selectAllCategoryAndNum")
    @ResponseBody
    public Result selectAllCategoryAndNum(){
        Result result=categoryService.selectAllCategoryAndNum();
        return result;
    }

    @RequestMapping("category")
    public String category(){

        return "category";
    }
    @RequestMapping("selectCategory")
    @ResponseBody
    public Result selectCategory(){

        return categoryService.selectCategoryAsNumber();

    }
    @RequestMapping("addCategory")
    @ResponseBody
    public Result addCategory(@RequestParam String new_category_name){
        return  categoryService.addCategory(new_category_name);
    }

    @RequestMapping("deleteCategoryById")
    @ResponseBody
    public Result deleteCategoryById(@RequestParam Integer category_id ){
        return categoryService.deleteCategoryById(category_id);


    }

    @RequestMapping("updateCategoryById")
    @ResponseBody
    public Result updateCategoryById(@RequestParam Integer category_id,@RequestParam String new_category_name){
        return categoryService.updateCategoryById(category_id,new_category_name);
    }


}
