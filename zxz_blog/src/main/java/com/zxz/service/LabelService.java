package com.zxz.service;



import com.zxz.util.Result;



/**
 * Created by limi on 2017/10/16.
 */
public interface LabelService {

    public Result selectAllLabel();

    public Result selectLabelAsNumber();



    public Result addLabel(String new_label_name);


    public Result deleteLabelById(Integer label_id );


    public Result updateLabelById(Integer label_id,String new_label_name);
}
