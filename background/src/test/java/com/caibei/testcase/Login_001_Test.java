package com.caibei.testcase;

import com.caibei.base.Base;
import org.testng.annotations.Test;

import java.util.Map;

public class Login_001_Test extends Base {
    @Test(dataProvider="testData", description = "测试类")
    public void test_001(Map<String, String> data){
        System.out.println(data.get("用户名"));
    }

}
