package com.caibei;

import com.caibei.common.SeleniumUtil;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.util.Properties;

public class TestDemo {
    WebDriver driver = null;
    SeleniumUtil au = new SeleniumUtil();
    public static void main(String[] args) throws Exception{
//        TestDemo t = new TestDemo();
//                t.setUp();
//        t.readProperty("data.properties");
        String s1 = "\u62b1\u6b49\uff0c\u60a8\u5173\u6ce8\u7684\u7528\u6237\u4e0d\u9519...\u5728sasa\u3002sasa\u3333";
        String str[] = s1.split("\\\\u");
        System.out.println(str[0]);
        System.out.println("\u62b1\u6b49\uff0c\u60a8\u5173\u6ce8\u7684\u7528\u6237\u4e0d\u9519...\u5728sasa\u3002sasa\u3333");
    }

    public void setUp() throws Exception{
        //System.setProperty("webdriver.chrome.driver", "D:\\chrome\\chromedriver\\chromedriver2.43\\chromedriver.exe");
        //driver = new ChromeDriver();
        driver = au.createDriver("chrome", "D:\\chrome\\chromedriver\\chromedriver2.43\\chromedriver.exe");
        driver.get("https://www.baidu.com");
        Thread.sleep(5000);
        driver.quit();
    }

    public void readProperty(String fileName) throws Exception{
        Properties p = new Properties();
        FileInputStream fis = new FileInputStream(fileName);
        p.load(fis);
        System.out.println(p.getProperty("browser"));
        System.out.println(p.getProperty("driverPath"));
    }
}
