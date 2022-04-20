package com.nari.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;

@Component
@Slf4j
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Value("${krpano.file-path}")
    private String krpanoFile;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String os = System.getProperty("os.name");
        //Windows操作系统
        if (os != null && os.toLowerCase().startsWith("windows")) {
            log.info(String.format("当前系统版本是:%s", os));
        } else if (os != null && os.toLowerCase().startsWith("linux")) {//Linux操作系统
            log.info(String.format("当前系统版本是:%s", os));
        } else { //其它操作系统
            log.info(String.format("当前系统版本是:%s", os));
        }

        if (!StringUtils.isEmpty(krpanoFile)) {
            if (!new File(krpanoFile).exists()) {
                log.error("krpano未安装,请在" + krpanoFile + "目录下安装！");
            }
        }

    }
}
