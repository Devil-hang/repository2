package com.caibei.common;

/*
Selenium常用工具类，封装selenium的常用方法
 */

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class SeleniumUtil {

    Logger log = Logger.getLogger(SeleniumUtil.class);
    WebDriver driver = null;

    //初始化driver
    public WebDriver createDriver(String browser, String driverPath){
        try{
            if(browser.toLowerCase().equals("chrome")){
                System.setProperty("webdriver.chrome.driver", driverPath);
                driver = new ChromeDriver();
            }else if(browser.toLowerCase().equals("firefox")){
                driver = new FirefoxDriver();
            }else if(browser.toLowerCase().equals("ie")){
                System.setProperty("webdriver.ie.driver", driverPath);
                DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
                //不使用保护模式
                dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                driver = new InternetExplorerDriver(dc);
            }else {
                log.error("传入的浏览器名称无法识别，初始化driver失败");
            }
        }catch (SessionNotCreatedException e){
            log.error("初始化driver失败.", e);
            return driver;
        }
        log.info("driver初始化成功。值为:" + driver);
        return driver;
    }

    //根据元素获取元素的定位值
    public String getLocatorByElement(WebElement element, String expectText){
        String text = element.getText();
        String locatorValue = null;
        try{
            locatorValue = text.substring(text.indexOf(expectText)+1, text.length()-1);
        }catch (Exception e){
            log.error("查找-->" + expectText + "失败", e);
        }
        return locatorValue;
    }

    //查找元素
    public WebElement findElementToBy(By by){
        WebElement element = null;
        log.info("正在通过-->" + by + "查找元素....");
        try{
            element = driver.findElement(by);
            log.info("找到元素");
        }catch (NoSuchElementException e){
            log.error("元素未找到异常", e);
        }
        return element;
    }

    //查找一组元素
    public List<WebElement> findElementsToBy(By by){
        List<WebElement> elements = null;
        log.info("正在通过-->" + by + "查找元素....");
        try {
            elements = driver.findElements(by);
            log.info("找到元素");
        }catch (NoSuchElementException e){
            log.error("元素未找到异常", e);
        }
        return elements;
    }

    //智能查找
    public WebElement findElement(final By by, int timeout){
        WebDriverWait wd = new WebDriverWait(driver, timeout);
        WebElement element = null;
        try{
            element = wd.until(new ExpectedCondition<WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    return findElementToBy(by);
                }
            });
        }catch (Exception e){
            log.error("未找到元素");
        }
        return element;
    }

    //获取文本值
    public String getTextToElement(By by){
        String text = null;
        try {
            text = findElementToBy(by).getText();
            log.info("获取元素-->" + by + "的文本值为:" + text);
        }catch (Exception e){
            log.info("获取元素-->" + by + "的文本值失败");
        }
        return text;
    }

    //打开浏览器
    public void openBrowser(String url){
        try{
            driver.get(url);
            log.info("成功打开-->" + url);
        }catch (Exception e){
            log.error("打开-->" + url + "失败");
        }
    }

    //关闭
    public void close(){
        driver.close();
        log.info("关闭窗口");
    }

    //浏览器前进
    public void forward(){
        driver.navigate().forward();
        log.info("浏览器前进");
    }

    //浏览器后退
    public void back(){
        driver.navigate().back();
        log.info("浏览器后退");
    }

    //浏览器刷新
    public void refresh(){
        driver.navigate().refresh();
        log.info("刷新浏览器");
    }

    //输入
    public void inputToElement(By by, String string){
        WebElement element = findElementToBy(by);
        try{
            element.sendKeys(string);
            log.info("在元素-->" + getLocatorByElement(element, ">") + "输入内容:" + string);
        }catch (Exception e){
            log.error("输入失败");
        }
    }

    //点击
    public void clickOnElement(By by){
        WebElement element = null;
        try{
            element.click();
            log.info("点击元素-->" + getLocatorByElement(element, ">"));
        }catch (Exception e){
            log.error("点击失败");
        }
    }

    //清除
    public void clearOnElement(By by){
        WebElement element = null;
        try {
            element.clear();
            log.info("成功清除元素-->"+ getLocatorByElement(element, ">") + "上的信息");
        }catch (Exception e){
            log.error("清除元素信息失败");
        }
    }

    //获取元素属性值
    public String getAttr(By by, String attributeName){
        WebElement element = null;
        String attribute = null;
        try {
            new TouchAction((AndroidDriver) driver);
            attribute = element.getAttribute(attributeName);
            log.info("获取元素-->" + getLocatorByElement(element, ">") + "的" + attributeName + "属性值为:" + attribute);
        }catch (Exception e){
            log.error("获取元素属性值失败");
        }
        return attribute;
    }


}
