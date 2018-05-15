package com.devil.page;

import org.openqa.selenium.By;

/**
 * 
 * @author Devil
 *门票购买页面元素及方法的封装
 */
public class TicketBuy {

//景点名称
	public static final By NAME_TEXT_VIEW = By.xpath("//android.webkit.WebView/android.view.View[2]");
//预订按钮
	public static final By YUDING_BUTTON_CLICK = By.xpath("//*[@content-desc='预定']");

}
