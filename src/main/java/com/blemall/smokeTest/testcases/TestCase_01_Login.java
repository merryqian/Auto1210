package com.blemall.smokeTest.testcases;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.blemall.smokeTest.page.LoginPage;
import com.test.base.TestBase;
import com.test.bean.TestExpect;
import com.test.bean.TestngListener;
import com.test.util.Log;
@Listeners(TestngListener.class)
public class TestCase_01_Login extends TestBase{
	@Test(dataProvider="providerMethod")
	public void loginBlemall(Map<String,String> param) throws IOException, InterruptedException
	{
		System.out.println("Good Morning,Merry!");
		String currentURL=this.getCurrentURL();
		LoginPage login=new LoginPage(driver);
		this.goTo(param.get("url"));
		WebElement el=login.getElement("location");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", el);
		login.getElement("login").click();
		login.getElement("username").sendKeys(param.get("username"));
		Log.logInfo("username= "+param.get("username"));
		login.getElement("password").sendKeys(param.get("password"));
		Log.logInfo("password= "+param.get("password"));
		login.getElement("loginButton").click();
		Log.logInfo("click login button");
		
	    Log.logInfo("currentURL= "+currentURL);
	    String exceptedURL=param.get("url");
	    Assert.assertEquals(currentURL, exceptedURL);
	    Log.logInfo("登录成功");
//		TestExpect expect = blemall.loginBlemall(param);
//		expect.setClassName(this.getClass().getName());
//		expect.setMethodName(Thread.currentThread().getStackTrace()[1].getMethodName());
//		expect.setBussinessDesp("百联用户登录");
//		
//		expectedList.add(expect);
	}
	
//	@Test
//	public void testLogin() throws InterruptedException{
//		TestExpect expect = new TestExpect();
//		expect.setExpect("测试期望�?");
//		expect.setActual("真实期望�?");
//		expect.setClassName(this.getClass().getName());
//		expect.setMethodName(Thread.currentThread().getStackTrace()[1].getMethodName());
//		expect.setBussinessDesp("这个是一个简单的测试");
//		Thread.sleep(5000);
//		expectedList.add(expect);
//	}
	
}
