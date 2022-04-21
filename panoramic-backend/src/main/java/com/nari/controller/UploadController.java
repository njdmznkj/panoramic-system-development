package com.nari.controller;

import com.nari.tools.DeleteFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@Slf4j
public class UploadController {

    @Value("${krpano.upload-path}")
    private String filePath;

    @Value("${krpano.tools-path}")
    private String krpanoFile;

    // 执行上传
    @GetMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, Model model) throws IOException {

        //获取用户名
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = "liuwenwen";
        // 获取上传文件名
        String filename = file.getOriginalFilename();
        String fileName = filename.substring(0, filename.length() - 5);
        // 定义上传文件保存路径
        String path = filePath + "rotPhoto/" + name;
        // 新建文件
        File filepath = new File(path, filename);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        try {
            // 写入文件
            file.transferTo(new File(path + File.separator + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 将src路径发送至html页面
        model.addAttribute("filename", "/images/rotPhoto/" + name + "\\" + filename);
        String cmd;
        String appction = krpanoFile + File.separator + "krpanotools.exe makepano -config=templates/vtour-normal.config -panotype=sphere " + filePath + "rotPhoto/";
        cmd = appction + name + "/" + filename;
        System.out.println("cmd >>>>" + cmd);
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String content = br.readLine();
            while (content != null) {
                System.out.println(content);
                content = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("执行完成.....");
        String[] wenname = new String[4];
        String pathfile = filePath + "rotPhoto/";
        wenname[0] = pathfile + name + "/vtour/usergoods.html";
        wenname[1] = pathfile + name + "/vtour/tour.swf";
        wenname[2] = pathfile + name + "/vtour/tour_testingserver.exe";
        wenname[3] = pathfile + name + "/vtour/tour_testingserver_macos";
        for (int i = 0; i < wenname.length; i++) {
            File filethree = new File(wenname[i]);
            filethree.delete();

        }
//            删除生成的重复的文件包
        String[] wennametwo = new String[2];
        wennametwo[0] = pathfile + name + "/vtour/plugins";
        wennametwo[1] = pathfile + name + "/vtour/skin";
        for (int i = 0; i < wennametwo.length; i++) {
            File filefour = new File(wennametwo[i]);
            DeleteFile deleteFile = new DeleteFile();
            deleteFile.deleteFile(filefour);
        }
        try {
            Pattern pattern = Pattern.compile("<include url=\"skin/vtourskin.xml\" />", Pattern.CASE_INSENSITIVE); // 要匹配的字段内容，正则表达式
            Matcher matcher = pattern.matcher("");
            List<String> lines = Files.readAllLines(Paths.get(pathfile + name + "/vtour/tour.xml")); // 读取文本文件
            for (int i = 0; i < lines.size(); i++) {
                matcher.reset(lines.get(i));
                if (matcher.find()) { // 匹配正则表达式
                    lines.remove(i);
                    lines.add(i, "<include url=\"../../../../skin/vtourskin.xml\" />");
                }
            }

            Files.write(Paths.get(pathfile + name + "/vtour/tour.xml"), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File oldFile = new File(filePath + "rotPhoto/" + name + "/vtour");
        if (!oldFile.exists()) {
            oldFile.createNewFile();
        }
        String rootPath = oldFile.getParent();
        File newFile = new File(rootPath + File.separator + fileName);
        if (oldFile.renameTo(newFile)) {
            log.info("修改成功!");
        } else {
            log.info("修改失败");
        }


        return "upload";
    }
}
