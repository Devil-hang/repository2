package com.caibei.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
读取属性文件
 */
public class ReadProperty {

    public static Map<String, String> getProperty(String filePath) throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(filePath);
        Map<String, String> map = new HashMap<String, String>();
        properties.load(fis);
        properties.values();
        for(Object k : properties.keySet()){

           map.put(k.toString(), properties.getProperty(k.toString()));
        }
        return map;
    }
}
