package com.zxz.web;


import com.zxz.service.LabelService;
import com.zxz.util.Result;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;




/**
 * Created by limi on 2017/10/16.
 */

@RestController

public class LabelController {

    @Autowired
    private LabelService labelService;


    @RequestMapping("/selectLabel")
    public Result selectLabel() {
        Result result = labelService.selectLabelAsNumber();
        return result;

    }
    @RequestMapping("/addLabel")
    public Result addLabel(@RequestParam  String new_label_name){
        return labelService.addLabel(new_label_name);
    }

    @RequestMapping("/deleteLabelById")
    public Result deleteLabelById(@RequestParam Integer label_id ){
        return labelService.deleteLabelById(label_id);
    }

    @RequestMapping("/updateLabelById")
    public Result updateLabelById(@RequestParam Integer label_id,@RequestParam String new_label_name){
        return  labelService.updateLabelById(label_id,new_label_name);
    }


}
