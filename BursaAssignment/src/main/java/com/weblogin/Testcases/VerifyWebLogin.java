package com.weblogin.Testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.weblogin.Pages.HomePage;
import com.weblogin.Pages.XLUtility;

public class VerifyWebLogin {
	WebDriver driver;

	@BeforeClass
	public void setup() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://www.bursamarketplace.com/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		HomePage home = new HomePage(driver);
		home.clickOnRegsiterMenu();
		// driver.findElement(By.xpath("//div[contains(text(),'Register')]")).click();

	}

	@Test(dataProvider = "LoginData")
	public void loginTest(String displayname, String email, String password) throws InterruptedException {

		WebElement disname = driver.findElement(By.name("signup-username"));
		disname.clear();
		disname.sendKeys(displayname);

		WebElement mail = driver.findElement(By.name("signup-email"));
		mail.clear();
		mail.sendKeys(email);

		WebElement pwd = driver.findElement(By.name("signup-password"));
		pwd.clear();
		pwd.sendKeys(password);

		driver.findElement(By.xpath("//button[@value='Sign Up']")).click();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement act_Msg = driver.findElement(By.xpath("//form[@id='login-signup']//div[@class='errmsg']"));

		String errMsg = act_Msg.getText();

		String exp_msg = "Captcha fields are mandatory";

		if (exp_msg.equalsIgnoreCase(errMsg)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}

	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {
		// get the data from excel
		String path = ".\\Files\\Registration.xlsx";
		XLUtility xlutil = new XLUtility(path);

		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);

		String loginData[][] = new String[totalrows][totalcols];

		for (int i = 1; i <= totalrows; i++) // 1
		{
			for (int j = 0; j < totalcols; j++) // 0
			{
				loginData[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
			}

		}

		return loginData;
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
