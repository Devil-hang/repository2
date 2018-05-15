package com.devil.utils;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;

import io.appium.java_client.AppiumDriver;

/**
 * 创建driver
 * @author Devil
 *
 */
public class SelectDriver {
	public AppiumDriver<WebElement> driver;
	public DesiredCapabilities capabilities;
	public String url;
	public String platformName;
	public String platformVersion;
	public String deviceName;
	public String appPackage;
	public String appActivity;
	public Boolean unicodeKeyboard;
	public String resetKeyboard;
	public int newCommandTimeout;
	public ITestContext context;
	public Boolean sessionOverride;
	public Boolean xiaochengxu;
	public Boolean noReset;
	public String automationName;
	
	public static Logger logger = Logger.getLogger(SelectDriver.class);
	public AppiumDriver<WebElement> selectDriver(AppiumUtil appium, ITestContext context) {
		url = context.getCurrentXmlTest().getParameter("serverURL");
		platformName = context.getCurrentXmlTest().getParameter("platformName");
		platformVersion = context.getCurrentXmlTest().getParameter("platformVersion");
		deviceName = context.getCurrentXmlTest().getParameter("deviceName");
		unicodeKeyboard = Boolean.parseBoolean(context.getCurrentXmlTest().getParameter("unicodeKeyboard"));
		resetKeyboard = context.getCurrentXmlTest().getParameter("resetKeyboard");
		sessionOverride = Boolean.parseBoolean(context.getCurrentXmlTest().getParameter("sessionOverride"));
		newCommandTimeout = Integer.parseInt(context.getCurrentXmlTest().getParameter("newCommandTimeout"));
		appPackage = context.getCurrentXmlTest().getParameter("appPackage");
		appActivity = context.getCurrentXmlTest().getParameter("appActivity");
		xiaochengxu = Boolean.parseBoolean(context.getCurrentXmlTest().getParameter("xiaochengxu"));
		noReset = Boolean.parseBoolean(context.getCurrentXmlTest().getParameter("noReset"));
		automationName = context.getCurrentXmlTest().getParameter("automationName");
		
		this.context = context;
		
		capabilities = new DesiredCapabilities();
		
		capabilities.setCapability("platformName", platformName);
		capabilities.setCapability("platformVersion", platformVersion);
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("unicodeKeyboard", unicodeKeyboard);
		capabilities.setCapability("resetKeyboard", resetKeyboard);
		capabilities.setCapability("sessionOverride", sessionOverride);
		capabilities.setCapability("noReset", noReset);
		capabilities.setCapability("automationName", automationName);
		if(xiaochengxu) {
			ChromeOptions options = new ChromeOptions();	
			options.setExperimentalOption("androidProcess", "com.tencent.mm:tools");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		}
		if(platformName.toLowerCase().contains("android")) {
			capabilities.setCapability("appPackage", appPackage);
			capabilities.setCapability("appActivity", appActivity);
			
			try{
				driver = appium.getDriver(platformName, url, capabilities);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			logger.info("driver初始化成功...driver is :"+ driver);
			context.setAttribute("APPIUM_DEIVER", driver);
			
			driver.manage().timeouts().implicitlyWait(newCommandTimeout, TimeUnit.SECONDS);
		}else if(platformName.toLowerCase().equals("ios")) {
			driver = appium.getDriver(platformName, url, capabilities);
			logger.info("driver初始化成功...driver is :"+ driver);
			context.setAttribute("APPIUM_DEIVER", driver);
			
			driver.manage().timeouts().implicitlyWait(newCommandTimeout, TimeUnit.SECONDS);
		}else{
			logger.error("不能初始化driver。因为传入的platformName："+platformName+"。是无效的");
			Assert.fail();
		}
		return driver;
	}
		
}
