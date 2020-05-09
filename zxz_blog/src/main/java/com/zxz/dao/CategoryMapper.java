package com.zxz.dao;

import com.zxz.po.Category;
import com.zxz.po.CategoryAndNum;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("SELECT * FROM category")
    public List<Category> selectAllCategory();

    @Select("SELECT * FROM category LIMIT 0,7")
    public List<Category> selectCategoryAsNumber();





    @Insert("INSERT INTO category(category_name) VALUE(#{new_category_name})")
    public int addCategory(String new_category_name);

    @Delete("DELETE FROM category WHERE id=#{category_id}")
    public int deleteCategoryById(Integer category_id );

    @Update("UPDATE category SET category_name=#{new_category_name} WHERE id=#{category_id}")
    public int updateCategoryById(Integer category_id,String new_category_name);

    @Select("select * from category where id=#{id}")
    public Category selectCategoryById(int id);


}
