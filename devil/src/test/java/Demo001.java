import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class Demo001 {
	AndroidDriver<WebElement> driver;
@BeforeClass
public void setUp() throws Exception{
	
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("platformName", "Android");
	capabilities.setCapability("deviceName", "98352d43");
	capabilities.setCapability("platformVersion", "7.1.1");
	capabilities.setCapability("appPackage", "com.jinqianbao.jmd");
	capabilities.setCapability("appActivity", "com.jinqianbao.jmd.ui.main.SplashActivity");
	capabilities.setCapability("automationName", "uiautomator2");
//	capabilities.setCapability("unicodeKeyboard", true);
//	capabilities.setCapability("resetKeyboard", true);
	
	driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
}

@AfterClass
public void tearDown() {
	driver.quit();
}

@Test
public void logIn() {
	driver.findElementById("com.jinqianbao.jmd:id/btn_login").click(); 
}

	
}
