package com.blemall.smokeTest.testcases;

import org.openqa.selenium.By;
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
	public void testI()
	{
		boolean flag = true;
		int i = 2;
		while(flag)
		{
			i++;
			if(i==7)
			{
				flag=false;
			}
			System.out.println("i= "+i);
		}
	}

}
