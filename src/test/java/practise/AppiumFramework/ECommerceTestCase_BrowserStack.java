package practise.AppiumFramework;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import utils.WaitUtils;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element; 
import static java.time.Duration.ofSeconds;


public class ECommerceTestCase_BrowserStack extends BaseTest {
	AndroidDriver<AndroidElement> driver=null;
	@BeforeTest
	public void init()
	{
//		clearPort();
//		startServer();
		driver=getAndroidDriver_BrowserStack();
		BaseTest.initialize();
		
	}
	
	@Test
//	@Parameters({"feature","ucId","tcId"})
	public void verifyTotalTest() throws InterruptedException {
		
//		driver.hideKeyboard();
		formPage.nameField.sendKeys("Hello");
		formPage.femaleOption.click();
		formPage.listOfCountries.click();
		utils.scrollToText("Argentina");
		formPage.getCountryEleByName("Argentina").click();
		formPage.btnLetsShop.click();
		
		homePage.addToCartLinks.get(0).click();
		homePage.addToCartLinks.get(0).click();
		
		homePage.viewCartButton.click();
		WaitUtils.hardWait(4000L);
		String amt1=checkoutPage.productPriceList.get(0).getText().replace("$", "");
		String amt2=checkoutPage.productPriceList.get(1).getText().replace("$", "");
		
		Double amtN1=Double.parseDouble(amt1);
		Double amtN2=Double.parseDouble(amt2);
		
		Double totalExp=amtN1+amtN2;
		
		String total=checkoutPage.totalAmountLbl.getText().replace("$", "");
		Double totalN=Double.parseDouble(total);
		
		System.out.println(totalExp.equals(totalN));
		
		Assert.assertEquals(totalExp,totalN);
		
		/*WebElement ele=driver.findElement(By.className("android.widget.CheckBox"));
		
		TouchAction t=new TouchAction(driver);
		t.tap(tapOptions().withElement(element(ele))).perform();
		
		WebElement tnc=driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
		new TouchAction(driver).longPress(longPressOptions().withElement(element(tnc)).withDuration(ofSeconds(2)).withPosition(PointOption.point(540,1733))).release().perform();
		
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();*/
		
		
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
//		service.stop();
	}

}
