package org.example.practice.commonutils.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.google.common.collect.Lists;

public abstract class FileUtil {

    public static String readTxt(File file) throws IOException {
        String s = "";
        InputStreamReader in = new InputStreamReader(new FileInputStream(file), "UTF-8");
        BufferedReader br = new BufferedReader(in);
        StringBuffer content = new StringBuffer();
        while ((s = br.readLine()) != null) {
            content = content.append(s);
        }
        return content.toString();
    }

    public static List<String> readTxtToList(File file) throws IOException {
        String s = "";
        InputStreamReader in = new InputStreamReader(new FileInputStream(file), "UTF-8");
        BufferedReader br = new BufferedReader(in);
        StringBuffer content = new StringBuffer();
        List<String> list = Lists.newArrayList();
        while ((s = br.readLine()) != null) {
            content = content.append(s);
            if (s.contains(";")) {
                list.add(content.toString());
                content = new StringBuffer();
            }
        }
        return list;
    }
}
