package com.devil.page;

import org.openqa.selenium.By;

import com.devil.utils.AppiumUtil;

/**
 * 
 * @author Devil
 *封装游客小程序登录页面元素、操作
 */
public class LoginPage {
	
//用户名输入框
	public static final By USERNAME_TEXT_INPUT = By.xpath("//*[@content-desc='输入手机号码登录']");
//获取验证码
	public static final By GETCODE_ICON_CLICK = By.xpath("//*[@content-desc='获取验证码']");
//验证码输入框
	public static final By CODE_TEXT_INPUT = By.xpath("//android.webkit.WebView/android.view.View/android.view.View");
//登录按钮
	public static final By LOGIN_BUTTON_CLICK = By.xpath("//*[@content-desc='登录']");
//登录页面标题：新疆旅游登录
	public static final By LOGINTITLE_TEXT_VIEW = By.xpath("//*[@content-desc='新疆旅游登录']");
//是否有重新登录的提示
	public static final By MASSAGE_BUTTON_CLICK = By.xpath("//*[@text='确定']");
//非注册手机号登录提示信息
	public static final By MASSAGE_TEXT_VIEW = By.xpath("//*[@text='非平台会员,请注册']");
//注册页面标题
	public static final By SINGINTITLE_TEXT_VIEW = By.xpath("//*[@text='新用户注册']");
//输入新密码
	public static final By NEWPASSWORD_TEXT_INPUT = By.xpath("//*[@content-desc='请输入新密码']");
//确认密码
	public static final By OKPASSWORD_TEXT_INPUT = By.xpath("//*[@content-desc='请再次输入密码']");
//注册按钮
	public static final By SINGIN_BUTTON_CLICK = By.xpath("//*[@content-desc='注册即同意']");
//协议链接
	public static final By XIEYI_LINK_CLICK = By.xpath("//*[@content-desc='设置密码']/following-sibling::android.view.View[2]/android.view.View[6]");
//协议内容
	public static final By SERVERTITLE_TEXT_VIEW = By.xpath("//*[@content-desc='新疆旅游电子商务平台服务条款']");
	/**
	 * 输入
	 */
	public static void inputText(AppiumUtil appium, String str, By by) {
		appium.typeContent(by, str);
	}
	
	/**
	 * 点击
	 */
	public static void clickButton(AppiumUtil appium, By by) {
		appium.click(by);
	}
	/**
	 * 判断页面是否正确跳转--存在相应的元素即认为跳转成功
	 */
	public static boolean isElement(AppiumUtil appium, By by) {
		return appium.doesElementsExist(by);
	}
	
}
