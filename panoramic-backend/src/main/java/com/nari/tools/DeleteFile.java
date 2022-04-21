package com.nari.tools;

import java.io.File;

public class DeleteFile {

    public void deleteFile(File file){
        if(file.exists()){
            if(file.isFile()){
                file.delete();
            }else if(file.isDirectory()){
                File files[] = file.listFiles();
                for(int i=0;i<files.length;i++){
                    this.deleteFile(files[i]);
                }
            }
            file.delete();
        }else{
            System.out.println("所删除的文件不存在！"+'\n');
        }
    }
}
