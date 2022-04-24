package com.nari.controller;

import com.alibaba.fastjson.JSONObject;
import com.nari.common.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@Slf4j
public class DisplayController {
    @Value("${krpano.upload-path}")
    private String filePath;

    @GetMapping("/display/list")
    public ResultBody display(){
        File file = new File(filePath+ "/rotPhoto");
        return ResultBody.success(file.list());
    }

    @GetMapping("/displayDetail/{projectId}")
    public ResultBody displayDetail(){

        return ResultBody.success();
    }

}
