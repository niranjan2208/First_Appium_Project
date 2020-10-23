package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import practise.AppiumFramework.BaseTest;

public class Utils extends BaseTest {
	public AndroidDriver<AndroidElement> driver;
	
	public Utils(AndroidDriver<AndroidElement> driver)
	{
		this.driver=driver;
	}
	
	public void scrollToText(String text)
	{
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
	}

}
