package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage {
	public AndroidDriver<AndroidElement> driver;
	public FormPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver=driver;
	}
	
	//driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Hello");
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	public WebElement nameField;
	
	//driver.findElement(By.xpath("//*[@text='Female']")).click();
	@AndroidFindBy(xpath="//*[@text='Female']")
	public WebElement femaleOption;
	
	@AndroidFindBy(id="android:id/text1")
	public WebElement listOfCountries;
	
//	driver.findElement(By.xpath("//*[@text='Argentina']")).click();
	public WebElement getCountryEleByName(String text)
	{
		WebElement countryEle=driver.findElement(By.xpath("//*[@text='"+text+"']"));
		return countryEle;
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	public WebElement btnLetsShop;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	public List<WebElement> productList;
	
}
