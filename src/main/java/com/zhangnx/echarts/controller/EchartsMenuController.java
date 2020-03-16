package com.zhangnx.echarts.controller;

import com.alibaba.fastjson.JSON;
import com.zhangnx.echarts.pojo.Echarts;
import com.zhangnx.echarts.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value ="/EchartsMenuController")
public class EchartsMenuController {

    @Autowired
    EchartsService echartsService;

    //环形图
    @RequestMapping(value ="/ringDiagram")
    public String ringDiagram(){
        return "echarts/ringDiagram";
    }

    //饼图
    @RequestMapping(value ="/pieChart")
    public String pieChart(){
        return "echarts/pieChart";
    }

    //柱状图
    @RequestMapping(value ="/barGraph")
    public String barGraph(){
        return "echarts/barGraph";
    }

    @RequestMapping(value ="/getlearnRate")
    @ResponseBody
    public String getlearnRate(HttpServletRequest request){

        List<Echarts>  echartsList = echartsService.getlearnRate();
        //将list转换成JSON格式
        String json = JSON.toJSONString(echartsList);
        return json;

    }


}
