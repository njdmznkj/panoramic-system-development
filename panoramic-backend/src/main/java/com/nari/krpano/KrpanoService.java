package com.nari.krpano;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class KrpanoService {

    @Value("${krpano.tools-path}")
    private String krpanoFile;

    public void run() {
        Process process = null;
        String[] cmd = {"cmd", "/C", "start " + krpanoFile + File.separator + "krpanotools.exe makepano -config=\\templates\\vtour-normal.config  D:\\tools\\quanjing.jpg"};
        try {
            process = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert process != null;
        InputStreamReader reader = new InputStreamReader(process.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader);
        String message;
        while (true) {
            try {
                if ((message = bufferedReader.readLine()) == null) {
                    break;
                }
                // 无用操作
//                System.out.println(new String(message.getBytes("UTF-8"), "UTF-8"));
                System.out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
