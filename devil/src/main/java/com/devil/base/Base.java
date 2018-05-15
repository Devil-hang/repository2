package com.devil.base;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import com.devil.configure.Log4j;
import com.devil.utils.AppiumUtil;
import com.devil.utils.ExcelData;
import com.devil.utils.SelectDriver;

import io.appium.java_client.AppiumDriver;

/**
 * 基类，用于初始化driver、结束drive、提供数据
 * @author Devil
 *
 */
public class Base {
public AppiumUtil appium;
public AppiumDriver<WebElement> driver = null;
public static Logger logger = Logger.getLogger(Base.class);
public int newCommandTimeout;
public String platformName;
public String appPackage;

@BeforeClass
	public void setUp(ITestContext context) {
	//使log4j生效
	Log4j.initLog(this.getClass().getSimpleName());	
//	platformName = context.getCurrentXmlTest().getParameter("platformName");
//	appPackage = context.getCurrentXmlTest().getParameter("appPackage");
//	System.out.println(appPackage+"appPackage");
//	newCommandTimeout = Integer.valueOf(context.getCurrentXmlTest().getParameter("newCommandTimeout"));
	appium = new AppiumUtil();
	driver = new SelectDriver().selectDriver(appium, context);
	
	/*进入小程序*/
	driver.findElementByXPath("//*[@text='发现']").click();
	
	driver.findElementByXPath("//*[@text='小程序']").click();
	driver.findElementByXPath("//*[@text='体验版']").click();
}

@AfterClass
	public void tearDown() {
	if(driver != null) {
		driver.quit();
	}else {
		logger.error("未成功初始化driver。测试结束!");
	}
}

@DataProvider(name = "testData")
	public Iterator<Object[]> getData(){
		String module = null;//模块名称
		String caseNum = null;//用例编号
		String caseName = this.getClass().getName();//caseName = com.devil.testcase.login.LoginPage_001_s_T
		
		module = caseName.substring(19, caseName.lastIndexOf("."));
		System.out.println(module);
		caseNum = caseName.substring(caseName.indexOf("_")+1, caseName.indexOf("_")+4);
		System.out.println(caseNum);
		return new ExcelData(module, caseNum);
	}
}
