package practise.AppiumFramework;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import pageobjects.CheckoutPage;
import pageobjects.FormPage;
import pageobjects.HomePage;
import utils.Utils;

public class BaseTest {

	static AndroidDriver<AndroidElement> driver;
	static AppiumDriverLocalService service;
	public static Utils utils;
	public static FormPage formPage;
	public static HomePage homePage;
	public static CheckoutPage checkoutPage;

	public static void initialize()
	{
		utils=new Utils(driver);
		formPage=new FormPage(driver);
		homePage=new HomePage(driver);
		checkoutPage=new CheckoutPage(driver);
	}

	public static boolean checkIfServerIsRunning(int port)
	{
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try
		{
			serverSocket=new ServerSocket(port);
			serverSocket.close();

		}
		catch(IOException e)
		{
			isServerRunning=true;
		}
		finally
		{
			serverSocket=null;
		}
		return isServerRunning;
	}

	public static AppiumDriverLocalService startServer()
	{
		if(!checkIfServerIsRunning(4723))
		{
			service=AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;
	}


	public static AndroidDriver<AndroidElement> getAndroidDriver() {
		try
		{
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\practise\\AppiumFramework\\global.properties");
			Properties prop=new Properties();
			prop.load(fis);
			File f=new File("src");
			File fs=new File(f,(String)prop.get("appName"));
			startEmulator();
			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, prop.get("deviceName"));
			cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
			driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return driver;

	}

	public static AndroidDriver<AndroidElement> getAndroidDriver_BrowserStack() {
		try
		{
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\practise\\AppiumFramework\\global.properties");
			Properties prop=new Properties();
			prop.load(fis);
			File f=new File("src");
			File fs=new File(f,(String)prop.get("appName"));
//			startEmulator();
			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setCapability("device", "Google Pixel 3");
			cap.setCapability("os_version", "9.0");
			cap.setCapability("project", "My First Project");
			cap.setCapability("build", "My First Build");
			cap.setCapability("browserstack.console", "verbose");
			cap.setCapability("name", "Bstack-[Java] Sample Test");
			cap.setCapability("app", "bs://199d2686b66ab83f1cf336e98ac36e02da6c2e95");
			driver=new AndroidDriver<AndroidElement>(new URL("https://niranjannandurka1:g7pv1xnpuysYbWriaL7Q@hub-cloud.browserstack.com/wd/hub"),cap);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return driver;

	}

	public static AndroidDriver<AndroidElement> getAndroidDriverForBrowser(String deviceName) {
		AndroidDriver<AndroidElement> driver=null;
		try {
			File f=new File("src");
			File fs=new File(f,"chromedriver.exe");
			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
			cap.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
			cap.setCapability("chromedriverExecutable",fs.getAbsolutePath());
			driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}
		return driver;
	}

	public static void startEmulator()

	{
		try {
			//D:\PracticeWorkspaces\First_Appium_Project_WS\AppiumFramework\src\main\java\resources\startEmulator.bat
			Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat");
			Thread.sleep(10000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public static void clearPort()
	{
		try 
		{
			Runtime.getRuntime().exec("taskkill /F /IM node.exe");
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void getScreenshot(String name)
	{
		try
		{
			File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\src\\main\\java\\screenshots\\"+name+".png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
