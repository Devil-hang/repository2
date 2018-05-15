package com.devil.page;

import org.openqa.selenium.By;

/**
 * 发现页面元素及操作
 * @author Devil
 *
 */
public class findPage {
	static String jdName;
//搜索框
	public static final By SHEARCH_TEXT_INPUT = By.xpath("//*[@content-desc='输入地州或景点名称']");
//最美目的地-查看全部
	public static final By SEARCH_BUTTONT_INPUT = By.xpath("//*[@content-desc='最美目的地']/following-sibling::android.view.View[1]");
//最美目的地-地区列表
	public static final By ADDRESS_LIST_CLICK = By.xpath("//android.webkit.WebView/android.view.View[6]/android.view.View");
//最美目的地-单个地州--可以获取地区的名称
	public static final By ADDRESS_VIEW_CLICK = By.xpath("//android.webkit.WebView/android.view.View[6]/android.view.View/android.view.View/android.view.View[2]");
//酷炫新疆风-单个门票--可以获取到名称
	public static final By XINJIANG_TICKET_CLICK = By.xpath("//android.view.View[@content-desc='酷炫新疆风']/following-sibling::android.view.View[1]/android.view.View/android.view.View/android.view.View[2]");
//最热资讯-查看全部
	public static final By NEWS_BUTTON_CLICKW = By.xpath("//*[@content-desc='最热资讯']/following-sibling::android.view.View[1]");
//资讯名称
	public static final By NEWS_TEXT_CLICK = By.xpath("//*[@content-desc='最热资讯']/following-sibling::android.view.View[2]/android.view.View/android.view.Viewandroid.view.View[2]");
//搜索页面
	public static final By SEARCHTITLE_TEXT_VIEW = By.xpath("//*[@text='搜索']");
//搜索的数据
	public static final By SEARCHPAGE_LIST_VIEW = By.xpath("//android.webkit.WebView/android.view.View[1]/following-sibling::android.view.View");
//酷炫新疆风列表
	public static final By XINJIANG_LIST_VIEW = By.xpath("//android.view.View[@content-desc='酷炫新疆风']/following-sibling::android.view.View[1]");
//资讯列表
	public static final By NEWSLIST_TEXT_VIEW = By.xpath("//*[@content-desc='最热资讯']/following-sibling::android.view.View[2]");
}
