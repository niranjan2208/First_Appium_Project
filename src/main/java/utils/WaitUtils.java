package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class WaitUtils {
	public AndroidDriver<AndroidElement> driver;
	
	public WaitUtils(AndroidDriver<AndroidElement> driver)
	{
		this.driver=driver;
	}
	
	public static void hardWait(Long duration)
	{
		try
		{
		Thread.sleep(duration);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
