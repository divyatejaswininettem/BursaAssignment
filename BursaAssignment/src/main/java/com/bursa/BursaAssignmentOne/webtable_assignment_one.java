package com.bursa.BursaAssignmentOne;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class webtable_assignment_one {

	public static void main(String[] args) {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://www.bursamalaysia.com/");
		driver.manage().window().maximize();
		String columnFrist = "//div[@class='tab-pane fade show active']//table[@class='table table-striped table-borderless table-carousel plain-table']//tbody//tr[";
		String columnlast = "]/child::*";

		String column;
		List<WebElement> bursaTableColumns;

		List<WebElement> bursaActiveTableRows = driver.findElements(By.xpath(
				"//div[@class='tab-pane fade show active']//table[@class='table table-striped table-borderless table-carousel plain-table']//tbody//tr"));

		for (int i = 1; i <= bursaActiveTableRows.size(); i++) {
			column = columnFrist + i + columnlast;

			bursaTableColumns = driver.findElements(By.xpath(column));

			for (int j = 0; j < bursaTableColumns.size(); j++) {
				if (j != 2) // to avoid the last done column
					System.out.print(bursaTableColumns.get(j).getText() + "  ");
			}
			System.out.println();
		}

		driver.close();

	}
}
