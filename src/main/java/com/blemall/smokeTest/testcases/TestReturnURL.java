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
		driver.get("http://m.bl.com/h5-web/page/view_Index.html");
		driver.manage().window().maximize();
		String handle=driver.getWindowHandle();
		System.out.println("handle="+handle);
		WebElement scroll=driver.findElement(By.xpath(".//*[@id='mySwipe2']/div/div/a/img"));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();", scroll);
		WebElement el=driver.findElement(By.xpath(".//*[@id='main']/div/section[4]/div/a"));
		el.click();
		driver.findElement(By.id("lgoinName")).sendKeys("18516042869");
		driver.findElement(By.id("pwd")).sendKeys("wobugaosuni1");
		driver.findElement(By.id("loginButton")).click();
		System.out.println("title="+driver.getCurrentUrl());
		driver.switchTo().window(handle);
		System.out.println("handle="+driver.getWindowHandle());
		System.out.println("title="+driver.getCurrentUrl());
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//driver.findElement(By.xpath(".//*[@id='main']/div/nav/div[1]/a/img")).click();
		
		try {
			driver.findElement(By.linkText("登录"));
			System.out.println("登录失败");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("登录成功");
		}
	}

}
