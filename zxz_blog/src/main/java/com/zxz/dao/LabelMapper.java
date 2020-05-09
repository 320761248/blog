package com.zxz.dao;



import com.zxz.po.Label;
import com.zxz.util.Result;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LabelMapper {

    @Select("SELECT * FROM label")
    public List<Label> selectAllLabel();

    @Select("SELECT * FROM label   LIMIT 0,10")
    public List<Label> selectLabelAsNumber();

    @Insert("INSERT INTO label(label_name) VALUE(#{new_label_name})")
    public int addLabel(String new_label_name);

    @Delete("DELETE FROM label WHERE id=#{label_id}")
    public int deleteLabelById(Integer label_id );

    @Update("UPDATE label SET label_name=#{new_label_name} WHERE id=#{label_id}")
    public int updateLabelById(Integer label_id,String new_label_name);

    @Select("select * from label where id=#{label_id}")
    public Label selectLabelById(Integer label_id);
}
