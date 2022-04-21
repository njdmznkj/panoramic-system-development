package com.nari.controller;

import com.nari.common.ResultBody;
import com.nari.exception.BizException;
import com.nari.krpano.KrpanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private KrpanoService krpanoService;

    @GetMapping("/getData")
    public ResultBody getData() {
        krpanoService.run();
        System.out.println("获取数据");
        if (true) {
            throw new BizException("-1", "用户姓名不能为空！");
        }
        return ResultBody.error("用户姓名不能为空");
    }
}
