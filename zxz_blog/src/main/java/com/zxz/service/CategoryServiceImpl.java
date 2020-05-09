package com.zxz.service;



import com.sun.org.apache.bcel.internal.classfile.Code;
import com.zxz.dao.ArticleMapper;
import com.zxz.dao.CategoryMapper;
import com.zxz.po.Category;
import com.zxz.po.CategoryAndNum;
import com.zxz.util.CodeMsg;
import com.zxz.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by limi on 2017/10/16.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ArticleMapper articleMapper;



    @Override
    public Result selectAllCategory() {
        List<Category> categories= categoryMapper.selectAllCategory();
        if(categories.size()==0){
            Result.error(CodeMsg.CURRENT_CATEGORY_EMPTY);
        }
        return Result.success(categories);
    }

    /**
     * 查询文章最多的5个类别
     * @return
     */

    @Override
    public Result selectCategoryAsNumber() {
        List<Category> categories=categoryMapper.selectCategoryAsNumber();
        if(categories.size()==0){
            return Result.error(CodeMsg.CURRENT_LABEL_EMPTY);
        }
        return Result.success(categories);
    }

    @Override
    public Result selectAllCategoryAndNum() {
        List<Category> categorys=categoryMapper.selectAllCategory();
        List<CategoryAndNum> categoryAndNums=new ArrayList<>();
        for(Category category:categorys){
            int num=articleMapper.selectArticleNumByCategoryId(category.getId());
            CategoryAndNum categoryAndNum=new CategoryAndNum();
            categoryAndNum.setId(category.getId());
            categoryAndNum.setCategory_name(category.getCategory_name());
            categoryAndNum.setArticle_num(num);
            categoryAndNums.add(categoryAndNum);
        }
        return Result.success(categoryAndNums);
    }

    @Override
    public Result addCategory(String new_category_name) {
        int res=categoryMapper.addCategory(new_category_name);
        if(res==0){
            return Result.error(CodeMsg.ADD_CATEGORY_ERROR);
        }
        return Result.success(CodeMsg.ADD_CATEGORY_SUCCESS);
    }

    @Override
    public Result deleteCategoryById(Integer category_id) {
        int res=categoryMapper.deleteCategoryById(category_id);
        if(res==0){
            return Result.error(CodeMsg.DELETE_CATEGORY_ERROR);
        }
        return Result.success(CodeMsg.DELETE_CATEGORY_SUCCESS);
    }

    @Override
    public Result updateCategoryById(Integer category_id, String new_category_name) {
        int res=categoryMapper.updateCategoryById(category_id,new_category_name);
        if(res==0){
            return Result.error(CodeMsg.UPDATE_CATEGORY_ERROR);
        }
        return Result.success(CodeMsg.UPDATE_CATEGORY_SUCCESS);
    }


}
