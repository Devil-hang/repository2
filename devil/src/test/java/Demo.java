
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.windows.WindowsDriver;

/**
 * 
 * @author Devil
 *原生和webview相互切换，要加上参数：desired_caps['recreateChromeDriverSessions'] = True
 *负否切换到原生后要手动结束chromedriver
 */
public class Demo {
	public static AndroidDriver<WebElement> driver;
	public static void main(String[] args) throws Exception {
		try {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "6.0.1");
		capabilities.setCapability("deviceName", "M92QACQ5D77M9");
		capabilities.setCapability("appPackage", "com.tencent.mm");
		capabilities.setCapability("appActivity", ".ui.LauncherUI");
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", "True");
		capabilities.setCapability("automationName", "uiautomator2");
		capabilities.setCapability("recreateChromeDriverSessions", true);

									
		capabilities.setCapability("noReset", true);
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("androidProcess", "com.tencent.mm:tools");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
//		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);
		
		driver.findElementByXPath("//*[@text='发现']").click();

		driver.findElementByXPath("//*[@text='小程序']").click();
		
		driver.findElementByXPath("//*[@text='体验版']").click();
//		driver.findElementByXPath("//*[@text='首旅如家酒店集团']").click();
		

			
		driver.findElementByXPath("//*[@text='确定']").click();
	
		Set<String> contect = driver.getContextHandles();
		for(String c : contect) {
			System.out.println(c);
			if(c.toLowerCase().contains("tools")) {
			driver.context(c);
			}
		}

		System.out.println(driver.getContext());
		Thread.sleep(5000);
		WebElement element = driver.findElement(By.xpath("//*[@content-desc='输入手机号码登录']"));
		element.click();
		adbInput("15827894540");	
//		Runtime.getRuntime().exec("cmd /c adb shell input text " + "15827894540");
//		Thread.sleep(5000);
//		element.sendKeys("15827894540");
//		System.out.println(element);
//		driver.findElementByXPath("//android.webkit.WebView/android.view.View[2]/android.view.View[2]").sendKeys("15827894540");
//		driver.findElementByXPath("//android.webkit.WebView/android.view.View[2]/android.view.View[2]/android.view.View").sendKeys("15827894540");
		
//		element.click();
//		element.sendKeys("15827894540");
		driver.quit();
		}
		catch (Exception e) {
			e.printStackTrace();
			driver.quit();

		}
}
	public static void adbInput(String string) {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("cmd /c adb shell input text " + string);
		}catch (Exception e) {
			System.out.println("cmd执行失败");
		}
	}
}
