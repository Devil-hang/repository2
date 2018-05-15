package com.devil.testcase.login;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.devil.base.Base;
import com.devil.page.LoginPage;

/**
 * 
 * @author Devil
 *登录成功
 */
public class LoginPage_001_LoginSuccessful_Test extends Base{
@Test(dataProvider = "testData", priority = 1)
	public void login_successful(Map<String, String> data) {
	
		
	
	try {
		logger.info("用例标题:" + data.get("用例标题"));
		if(LoginPage.isElement(appium, LoginPage.MASSAGE_BUTTON_CLICK)) {
			
			LoginPage.clickButton(appium, LoginPage.MASSAGE_BUTTON_CLICK);
		}

		LoginPage.clickButton(appium, LoginPage.USERNAME_TEXT_INPUT);
		Thread.sleep(2000);		
		appium.adbInput(data.get("用户名"));
		Thread.sleep(5000);
	LoginPage.clickButton(appium, LoginPage.GETCODE_ICON_CLICK);
	logger.info("登录成功");
	}catch (Exception e) {
		logger.error("登录失败", e);
		Assert.fail();
	}
}
	
}
