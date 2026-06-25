package com.hy.controller;

import com.hy.pojo.Result;
import com.hy.service.StaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author zh
 * @Description TODO
 * @createTime 2026/6/2
 */
@RestController
@RequestMapping("/sta")
public class StaController {

    @Autowired
    private StaService staService;

    //1.获取医院总数
    @RequestMapping("/getHosCount")
    public Result getHosCount(){
        Integer count = staService.getHosCount();
        return new Result("200", "查询成功", count);
    }

    //2.获取患者总数
    @RequestMapping("/getPatCount")
    public Result getPatCount(){
        Integer count = staService.getPatCount();
        return new Result("200", "查询成功", count);
    }

    //5.获取患者年龄分布
    @RequestMapping("/getPatAge")
    public Result getPatAge(){
        List<Map<String, Integer>> ages = staService.getPatAge();
        return new Result("200", "查询成功", ages);
    }

    //3.获取各医院患者数量
    @RequestMapping("/getHosPatCount")
    public Result getHosPatCount(){
        List<Map<String, Integer>>  hosPatCount = staService.getHosPatCount();
        return new Result("200", "查询成功", hosPatCount);
    }



}
