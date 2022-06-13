package com.weblogin.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	
	WebDriver driver;
	By register = By.xpath("//div[contains(text(),'Register')]");
	public HomePage(WebDriver driver)
	{
	this.driver=driver;
	}
     public void clickOnRegsiterMenu() {
    	 driver.findElement(register).click();
     }
}