package com.blemall.smokeTest.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class TestReturnURL {
	@Test
	public void testURL()
	{
		WebDriver driver=new FirefoxDriver();
		driver.get("http://www.baidu.com");
		String handle=driver.getCurrentUrl();
		System.out.println("handle="+handle);
		driver.manage().window().maximize();
		driver.findElement(By.xpath(".//*[@id='u1']/a[2]")).click();
		String handle2=driver.getCurrentUrl();
		System.out.println("handle="+handle2);
		
	}

}
