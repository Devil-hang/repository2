package com.caibei.base;

import com.caibei.common.Excel;
import com.caibei.common.SeleniumUtil;
import com.caibei.config.Log;
import com.caibei.config.ReadProperty;
import com.caibei.config.ZTestReport;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.*;

/*
Base类。所有测试类的基类
作用：初始化/结束driver、为测试用例提供测试数据
 */
@Listeners({ZTestReport.class})
public class Base {
//    static Logger log = Logger.getLogger(Base.class);
//    SeleniumUtil seleniumUtil;
//    WebDriver driver = null;
    Map<String, String> properties = new HashMap<String, String>();
//
    @BeforeSuite
    public Map<String, String> getProperty(){
        Log.initLog(this.getClass().getSimpleName());//使日志文件生效
        try {
            properties = ReadProperty.getProperty("data.properties");
//            System.out.println(properties.get("fileName"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
//    @BeforeClass
//    public void setUp() {
//        seleniumUtil = new SeleniumUtil();
//        try {
//            Map<String, String> data = ReadProperty.getProperty("data.properties");
//            driver = seleniumUtil.createDriver(data.get("browser"), data.get("driverPath"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @AfterClass
//    public void tearDown(){
//        if(driver != null){
//            driver.quit();
//        }
//    }

    @DataProvider(name = "testData")
    public Object[][] getData(){
        String module;
        String fileName;
        //caseName = com.devil.testcase.login.Login_001_Test
        //String caseName = this.getClass().getName();
        String className = this.getClass().getSimpleName();//类名
        module = className.substring(0, className.indexOf("_")).toLowerCase();
        fileName = properties.get("fileName");
        List<Map<String, String>> list = Excel.getTestData(fileName, module);
        int length = list.size();
        Object[][] obj = new Object[length][1];
        for(int i =0;i<length;i++) {
            obj[i][0] = list.get(i);
        }
        return obj;
    }


    @BeforeClass
    public void setUp(){
        System.out.println("setUp开始执行");
    }
    @AfterClass
    public void tearDown(){
        System.out.println("tearDown开始执行");
    }
}
