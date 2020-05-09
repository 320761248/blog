package com.zxz.service;


import com.zxz.dao.LabelMapper;
import com.zxz.po.Category;
import com.zxz.po.Label;
import com.zxz.util.CodeMsg;
import com.zxz.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by limi on 2017/10/16.
 */
@Service
public class LabelServiceImpl implements LabelService {
    @Autowired
    private LabelMapper labelMapper;

    @Override
    public Result selectAllLabel() {
        List<Label> labels=labelMapper.selectAllLabel();
        if(labels.size()==0){
            return Result.error(CodeMsg.CURRENT_LABEL_EMPTY);
        }
        return Result.success(labels);
    }

    @Override
    public Result selectLabelAsNumber() {
        List<Label> labels=labelMapper.selectLabelAsNumber();
        if(labels.size()==0){
            return Result.error(CodeMsg.CURRENT_CATEGORY_EMPTY);
        }
        return Result.success(labels);
    }

    @Override
    public Result addLabel(String new_label_name) {
        int res=labelMapper.addLabel(new_label_name);
        if(res==0){
            return Result.error(CodeMsg.ADD_LABEL_ERROR);
        }
        return Result.success(CodeMsg.ADD_LABEL_SUCCESS);

    }

    @Override
    public Result deleteLabelById(Integer label_id) {
        int res=labelMapper.deleteLabelById(label_id);
        if(res==0){
            return Result.error(CodeMsg.DELETE_LABEL_ERROR);
        }
        return Result.success(CodeMsg.DELETE_LABEL_SUCCESS);
    }

    @Override
    public Result updateLabelById(Integer label_id, String new_label_name) {
        int res=labelMapper.updateLabelById(label_id,new_label_name);
        if(res==0){
            return Result.error(CodeMsg.UPDATE_LABEL_ERROR);
        }
        return Result.success(CodeMsg.UPDATE_LABEL_ERROR);
    }


}
