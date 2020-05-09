package com.zxz.service;



import com.zxz.po.Category;
import com.zxz.util.Result;

import java.util.List;

/**
 * Created by limi on 2017/10/16.
 */
public interface CategoryService {

    public Result selectAllCategory();

    public Result selectCategoryAsNumber();

    public Result selectAllCategoryAndNum();
    public Result addCategory(String new_category_name);


    public Result deleteCategoryById(Integer category_id );


    public Result updateCategoryById(Integer category_id,String new_category_name);
}
