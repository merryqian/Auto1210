package com.blemall.smokeTest.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class TestParm {
//	 @Test
//	 public void testParam()
//	 {
//		WebDriver driver=new FirefoxDriver();
//		driver.get("http://m.st.bl.com/h5-web/page/view_Index.html?pageNumber=1&pageSize=5");
//		int index=1;
//		WebElement el=driver.findElement(By.xpath(".//*[@id='main']/div/nav/div['"+index+"']/a/img"));
//	     el.click();
//	     
//	 }
	@Test
	public void getIntoView()
	{
		
		WebDriver driver=new FirefoxDriver();
		driver.get("http://m.bl.com/h5-web/page/view_Index.html?pageNumber=1&pageSize=5");
		driver.manage().window().maximize();
		driver.findElement(By.xpath(".//*[@id='main']/div/header/div")).click();
		driver.findElement(By.id("Keyserarch")).sendKeys("水果");
		driver.findElement(By.id("Keyserarch")).sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 String goodsXpath=".//*[@id='goodslist']/li[2]/a/span[2]/span[1]";
			WebElement goodsName = driver.findElement(By
					.xpath(goodsXpath));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();", goodsName);
		driver.findElement(By.xpath(".//*[@id='goodslist']/li[2]/a/span[2]/span[3]/span/span")).click();
	}

}
