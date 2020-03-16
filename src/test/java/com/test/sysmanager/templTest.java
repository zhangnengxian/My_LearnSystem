package com.test.sysmanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class templTest {
    public static void main(String[] args) {
        String path="E:\\servers\\apache-tomcat-8.0.52\\logs\\project-web-error.log";
        String s = readFileContent(path);
        System.out.println(s);
    }
    public static String readFileContent(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                if (tempStr.contains("ERROR")) {
                    sbf.append("<span style='color:red'><b>");
                    sbf.append(tempStr).append("<></span>").append("<br/>");
                }else{
                    sbf.append(tempStr).append("<br/>");
                }
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }
}
