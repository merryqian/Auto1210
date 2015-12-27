package com.blemall.smokeTest.testcases;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.blemall.smokeTest.page.LoginPage;
import com.test.base.TestBase;
import com.test.bean.Config;
import com.test.bean.TestExpect;
import com.test.bean.TestngListener;
import com.test.util.ExcelReader;
import com.test.util.Log;
import com.test.util.ScreenShot;
import com.test.util.TimeString;
//@Listeners(TestngListener.class)
public class TestCase_01_Login extends TestBase{
	TimeString ts = new TimeString();
	String timeString = ts.getDate()  ;
	ScreenShot screen = new ScreenShot(driver);
	//登录页面的UI显示 - 百联logo
@Test
public void loginH5Logo() throws IOException, InterruptedException
{
	LoginPage lp=new LoginPage(driver);
	String logoExpected="http://m.bl.com/h5-web/ui/h5resource/css/i/logo-1.png";
	//判断H5首页是否能打开
	try {
		this.goTo(Config.pathURL);
		Log.logInfo("打开首页连接=" + Config.pathURL);
		Log.logInfo("首页打开成功");
		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
				"登录页面的UI显示 - 百联logo","H5打开首页");
	} catch (Exception e) {
		Log.logInfo("首页打开失败");
		Assert.fail("首页打开失败");
	}
	//因为首页的登录按钮首屏未显示，故使用JS方法先定位登录的按钮
	//选择的定位点为登录上面的一个资源位
	WebElement scrollView = lp.getElement("location");
	this.getIntoView(scrollView);
	WebElement loginEntrance=lp.getElementNoWait("loginEntrance");
	loginEntrance.click();
	//判断登录页面的图片地址是否是预期的地址，如果不对也有可能是更换了图片地址
	WebElement logo=lp.getElement("logo");
	if(logo.getAttribute("src").equals(logoExpected))
	{
		Log.logInfo("logo图片URL= "+logo.getAttribute("src"));
		Log.logInfo("\"登录页面的UI显示 - 百联logo\" 测试场景通过");
		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
				"登录页面的UI显示 - 百联logo","检查通过");
	}
	else{
		Log.logInfo("\"登录页面的UI显示 - 百联logo\" 测试场景不通过");
		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
				"登录页面的UI显示 - 百联logo","检查不通过");
		Assert.fail("登录页面的logo图片地址不对，或者其他原因，请具体");
	}

}
//登录页面的UI显示 - 用户名输入框
@Test
public void userNameDefaultText() throws IOException, InterruptedException
{
	LoginPage lp=new LoginPage(driver);
	//判断H5首页是否能打开
	try {
		this.goTo(Config.pathURL);
		Log.logInfo("打开首页连接=" + Config.pathURL);
		Log.logInfo("首页打开成功");
	} catch (Exception e) {
		Log.logInfo("首页打开失败");
		Assert.fail("首页打开失败");
	}
	//因为首页的登录按钮首屏未显示，故使用JS方法先定位登录的按钮
   //选择的定位点为登录上面的一个资源位
	WebElement scrollView = lp.getElement("location");
	this.getIntoView(scrollView);
	WebElement loginEntrance=lp.getElementNoWait("loginEntrance");
	loginEntrance.click();
	WebElement username=lp.getElement("userName");
	if(username.isDisplayed())
	{
		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
				"登录页面的UI显示 - 用户名输入框","打开登录页面");
		String actualText=username.getAttribute("placeholder");
		String expectedText="用户名/手机/邮箱/实体卡号";
		if(actualText.equals("expectedText"))
		{
			Log.logInfo("用户名输入框中默认预期显示="+expectedText);
			Log.logInfo("用户名输入框中默认实际显示="+actualText);
			Log.logInfo("\"登录页面的UI显示 - 用户名输入框\" 测试场景通过");
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录页面的UI显示 - 用户名输入框","检查用户名输入框");
			Assert.assertTrue(true);
		}
		else{
			Log.logInfo("用户名输入框中默认显示文字显示异常");
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录页面的UI显示 - 用户名输入框","用户名输入框中默认显示文字显示异常");
			Assert.fail();
		}
	}
	else{
		Log.logInfo("登录页面打开异常");
		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
				"登录页面的UI显示 - 用户名输入框","H5显示异常");
		Assert.fail("登录页面打开异常");
	}
}
	//登录页面的UI显示 - 密码输入框
	@Test
	public void userPasswordDefaultText() throws IOException, InterruptedException
	{
	LoginPage lp=new LoginPage(driver);
	//判断H5首页是否能打开
	try {
		this.goTo(Config.pathURL);
		Log.logInfo("打开首页连接=" + Config.pathURL);
		Log.logInfo("首页打开成功");
	} catch (Exception e) {
		Log.logInfo("首页打开失败");
		Assert.fail("首页打开失败");
	}
	//因为首页的登录按钮首屏未显示，故使用JS方法先定位登录的按钮
   //选择的定位点为登录上面的一个资源位
	WebElement scrollView = lp.getElement("location");
	this.getIntoView(scrollView);
	WebElement loginEntrance=lp.getElementNoWait("loginEntrance");
	loginEntrance.click();
	WebElement password=lp.getElement("password");
	if(password.isDisplayed())
	{
		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
				"登录页面的UI显示 - 密码输入框","打开登录页面");
		String actualText=password.getAttribute("placeholder");
		String expectedText="请输入密码";
		if(actualText.equals(expectedText))
		{
			Log.logInfo("密码输入框中默认预期显示="+expectedText);
			Log.logInfo("密码输入框中默认实际显示="+actualText);
			Log.logInfo("\"登录页面的UI显示 - 密码输入框\" 测试场景通过");
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录页面的UI显示 - 密码输入框","检查密码输入框");
			Assert.assertTrue(true);
		}
		else{
			Log.logInfo("密码输入框中默认显示文字显示异常");
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录页面的UI显示 - 密码输入框","密码输入框中默认显示文字显示异常");
			Assert.fail();
		}
	}
	else{
		Log.logInfo("登录页面打开异常");
		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
				"登录页面的UI显示 - 密码输入框","H5登录页面打开异常");
		Assert.fail("登录页面打开异常");
	}

}
	//登录页面的UI显示 - 密码输入框
	@Test()
	public void forgetPasswordLink() throws IOException, InterruptedException
	{
		LoginPage lp=new LoginPage(driver);
		//判断H5首页是否能打开
		try {
			this.goTo(Config.pathURL);
			Log.logInfo("打开首页连接=" + Config.pathURL);
			Log.logInfo("首页打开成功");
		} catch (Exception e) {
			Log.logInfo("首页打开失败");
			Assert.fail("首页打开失败");
		}
		//因为首页的登录按钮首屏未显示，故使用JS方法先定位登录的按钮
	   //选择的定位点为登录上面的一个资源位
		WebElement scrollView = lp.getElement("location");
		this.getIntoView(scrollView);
		WebElement loginEntrance=lp.getElementNoWait("loginEntrance");
		loginEntrance.click();
		WebElement forgetLink=lp.getElement("forgetPassword");
		String actualText=forgetLink.getAttribute("href");
		String expectedText="http://m.bl.com/h5-web/member/forgetPassword.do";
		if(forgetLink.isDisplayed())
	    {
		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
				"登录页面的UI显示 - “忘记密码”链接","登录页面展示忘记密码");
		if((actualText.equals(expectedText))&&forgetLink.getText().equals("忘记密码？"))
		{
			Log.logInfo("密码输入框中默认预期显示="+expectedText);
			Log.logInfo("密码输入框中默认实际显示="+actualText);
			Log.logInfo("\"登录页面的UI显示 - “忘记密码”链接\" 测试场景通过");
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录页面忘记密码链接","忘记密码链接未变");
			forgetLink.click();
			if(lp.getElement("forgetPasswordText").isDisplayed())
			{
				Log.logInfo("忘记密码链接打开正常");
				screen.snapshot((TakesScreenshot) driver, timeString, "Login",
						"登录页面忘记密码链接","忘记密码链接打开正常");
				Assert.assertTrue(true);
			}
			else{
				Log.logInfo("忘记密码链接打开异常");
				screen.snapshot((TakesScreenshot) driver, timeString, "Login",
						"登录页面忘记密码链接","忘记密码链接打开异常");
				Assert.fail("忘记密码链接打开异常");
			}
		
		}
		else{
			Log.logInfo("密码输入框中默认显示文字显示异常");
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录页面忘记密码链接","忘记密码链接异常");
			Assert.fail();
		}
		}
		else{
			Log.logInfo("登录页面打开异常");
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录页面忘记密码链接","H5登录页面打开异常");
			Assert.fail("登录页面打开异常");
		}
		}
 //登录页面的UI显示 - 密码输入框
 @Test
 public void freeRegisterLink() throws IOException, InterruptedException
  {
	LoginPage lp=new LoginPage(driver);
	//判断H5首页是否能打开
	try {
		this.goTo(Config.pathURL);
		Log.logInfo("打开首页连接=" + Config.pathURL);
		Log.logInfo("首页打开成功");
	} catch (Exception e) {
		Log.logInfo("首页打开失败");
		Assert.fail("首页打开失败");
	}
	//因为首页的登录按钮首屏未显示，故使用JS方法先定位登录的按钮
   //选择的定位点为登录上面的一个资源位
	WebElement scrollView = lp.getElement("location");
	this.getIntoView(scrollView);
	WebElement loginEntrance=lp.getElementNoWait("loginEntrance");
	loginEntrance.click();
	WebElement freeRegister=lp.getElement("freeRegister");
	String actualText=freeRegister.getAttribute("href");
	String expectedText="http://m.bl.com/h5-web/member/memberRegister_viewStepOne.html";
	if(freeRegister.isDisplayed())
    {
	  screen.snapshot((TakesScreenshot) driver, timeString, "Login",
			"登录页面的免费注册","登录页面展示免费注册");
	if((actualText.equals(expectedText))&&freeRegister.getText().equals("免费注册"))
	{
		Log.logInfo("登录页面展示免费注册="+actualText);
		Log.logInfo("\"登录页面的UI显示 - “免费注册”链接\" 测试场景通过");
		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
				"登录页面免费注册链接","免费注册链接未变");
		freeRegister.click();
		if(lp.getElement("forgetPasswordText").isDisplayed())
		{
			Log.logInfo("免费注册链接打开正常");
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录页面免费注册链接","免费注册链接打开正常");
			Assert.assertTrue(true);
		}
		else{
			Log.logInfo("免费注册链接打开异常");
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录页面免费注册链接","免费注册链接打开异常");
			Assert.fail("免费注册链接打开异常");
		}
	
	}
	}
	else{
		Log.logInfo("登录页面打开异常");
		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
				"登录页面免费注册链接","H5登录页面打开异常");
		Assert.fail("登录页面打开异常");
	   }
	}
//登录页面的UI显示 - “实体卡首次登录”链接
@Test
public void solidCardLoginLink() throws IOException, InterruptedException
 {
	LoginPage lp=new LoginPage(driver);
	//判断H5首页是否能打开
	try {
		this.goTo(Config.pathURL);
		Log.logInfo("打开首页连接=" + Config.pathURL);
		Log.logInfo("首页打开成功");
	} catch (Exception e) {
		Log.logInfo("首页打开失败");
		Assert.fail("首页打开失败");
	}
	//因为首页的登录按钮首屏未显示，故使用JS方法先定位登录的按钮
  //选择的定位点为登录上面的一个资源位
	WebElement scrollView = lp.getElement("location");
	this.getIntoView(scrollView);
	WebElement loginEntrance=lp.getElementNoWait("loginEntrance");
	loginEntrance.click();
	WebElement solidButton=lp.getElement("solidButton");
	this.getIntoView(solidButton);
	String actualText=solidButton.getAttribute("href");
	String expectedText="http://m.bl.com/h5-web/member/forgetPassword.do?memberCard=1";
	WebElement el=lp.getElement("solidClick");
	if(el.isDisplayed())
   {
	  screen.snapshot((TakesScreenshot) driver, timeString, "Login",
			"实体卡首次登录","实体卡首次登录");
	if((actualText.equals(expectedText))&&solidButton.getText().equals("实体卡首次登录"))
	{
		Log.logInfo("实体卡首次登录展示="+expectedText);
		Log.logInfo("\"登录页面的UI显示 - “实体卡首次登录”链接\" 测试场景通过");
		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
				"登录页面实体卡首次登录链接","实体卡首次登录链接未变");
         el.click();
         WebElement title=lp.getElement("solidLoginTitle");
		if(title.isDisplayed()&&title.getText().equals("实体卡首次登陆"))
		{
			Log.logInfo("实体卡首次登录链接打开正常");
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录页面实体卡首次登录链接","实体卡首次登录链接打开正常");
			Assert.assertTrue(true);
		}
		else{
			Log.logInfo("实体卡首次登录链接打开异常");
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录页面实体卡首次登录链接","实体卡首次登录链接打开异常");
			Assert.fail("实体卡首次登录链接打开异常");
		}
	
	}
	else{
		Log.logInfo("实体卡登录没看到吗？");
	}
	}
	else{
		Log.logInfo("登录页面打开异常");
		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
				"登录页面实体卡首次登录链接","H5登录页面打开异常");
		Assert.fail("登录页面打开异常");
	   }
	}

//登录页面登录 - 密码错误提示
@Test(dataProvider="providerMethod")
public void wrongPasswordAlert(Map<String, String> param) throws IOException, InterruptedException
{
	LoginPage lp=new LoginPage(driver);
	//判断H5首页是否能打开
	try {
		this.goTo(Config.pathURL);
		Log.logInfo("打开首页连接=" + Config.pathURL);
		Log.logInfo("首页打开成功");
	} catch (Exception e) {
		Log.logInfo("首页打开失败");
		Assert.fail("首页打开失败");
	}
	//因为首页的登录按钮首屏未显示，故使用JS方法先定位登录的按钮
//选择的定位点为登录上面的一个资源位
	WebElement scrollView = lp.getElement("location");
	this.getIntoView(scrollView);
	WebElement loginEntrance=lp.getElementNoWait("loginEntrance");
	loginEntrance.click();
	lp.getElement("userName").sendKeys(param.get("username"));
	lp.getElement("password").sendKeys(param.get("password"));
	lp.getElement("loginButton").click();
	String expectedAlert="用户名或者密码错误!";
	WebElement alert=lp.getElement("wrongAlert");
	if(alert.isDisplayed())
	{
		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
				"登录页面登录 - 密码错误提示","登录密码错误");
		if(alert.getText().contains(expectedAlert))
		{
		  Log.logInfo("密码错误提示= "+alert.getText());
		  Log.logInfo("登录页面登录 - 密码错误提示 测试场景通过");
		}
		else{
			Log.logInfo("登录页面登录 - 密码错误提示 测试场景不通过");
			Assert.fail("登录页面登录 - 密码错误提示");
		}
		Assert.assertTrue(true);
	}
	else{
		Log.logInfo("登录页面登录 - 错误密码提示弹出异常");
		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
				"登录页面登录 - 密码错误提示","弹出错误提示");
		Assert.fail("登录页面登录 - 密码错误提示");
	}
}
//登录名不存在-错误提示
@Test(dataProvider="providerMethod")
public void usernameNotExist(Map<String,String> param) throws IOException, InterruptedException
{
	LoginPage lp=new LoginPage(driver);
	//判断H5首页是否能打开
	try {
		this.goTo(Config.pathURL);
		Log.logInfo("打开首页连接=" + Config.pathURL);
		Log.logInfo("首页打开成功");
	} catch (Exception e) {
		Log.logInfo("首页打开失败");
		Assert.fail("首页打开失败");
	}
	//因为首页的登录按钮首屏未显示，故使用JS方法先定位登录的按钮
   //选择的定位点为登录上面的一个资源位
	WebElement scrollView = lp.getElement("location");
	this.getIntoView(scrollView);
	WebElement loginEntrance=lp.getElementNoWait("loginEntrance");
	loginEntrance.click();
	lp.getElement("userName").sendKeys(param.get("username"));
	lp.getElement("password").sendKeys(param.get("password"));
	lp.getElement("loginButton").click();
	String expectedAlert="此账号不存在!";
	WebElement alert=lp.getElement("wrongAlert");
	if(alert.isDisplayed())
	{
		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
				"登录名不存在-错误提示","登录密码错误");
		if(alert.getText().contains(expectedAlert))
		{
		  Log.logInfo("= "+alert.getText());
		  Log.logInfo("\"登录名不存在-错误提示\" 测试场景通过");
		}
		else{
			Log.logInfo("\"登录名不存在-错误提示\"测试场景不通过");
			Assert.fail("登录页面登录 - 登录名不存在提示");
		}
		Assert.assertTrue(true);
	}
	else{
		Log.logInfo("\"登录名不存在-错误提示\"测试场景不通过");
		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
				"登录名不存在-错误提示","错误提示");
		Assert.fail("登录页面登录 - 登录名不存在");
	}
}
//登录页面登录 - 用户名和密码正确
@Test(dataProvider="providerMethod")
public void loginSuccess(Map<String,String> param) throws IOException, InterruptedException
{
	LoginPage lp=new LoginPage(driver);
	//判断H5首页是否能打开
	try {
		this.goTo(Config.pathURL);
		Log.logInfo("打开首页连接=" + Config.pathURL);
		Log.logInfo("首页打开成功");
	} catch (Exception e) {
		Log.logInfo("首页打开失败");
		Assert.fail("首页打开失败");
	}
	//因为首页的登录按钮首屏未显示，故使用JS方法先定位登录的按钮
 //选择的定位点为登录上面的一个资源位
	WebElement scrollView = lp.getElement("location");
	this.getIntoView(scrollView);
	WebElement loginEntrance=lp.getElementNoWait("loginEntrance");
	loginEntrance.click();
	lp.getElement("userName").sendKeys(param.get("username"));
	lp.getElement("password").sendKeys(param.get("password"));
	screen.snapshot((TakesScreenshot) driver, timeString, "Login",
			"登录成功","登录页面输入用户名、密码");
	lp.getElementNoWait("loginButton").click();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	try {
		driver.findElement(By.linkText("登录"));
		Log.logInfo("登录失败");
		Assert.fail("登录失败");
	} catch (Exception e) {
		Log.logInfo("登录成功");
		Assert.assertTrue(true);
	}
	
     }
//登录页面登录-实体卡登录
  @Test
  public void solidCardLogin() throws IOException, InterruptedException
  {
  	LoginPage lp=new LoginPage(driver);
  	//判断H5首页是否能打开
  	try {
  		this.goTo(Config.pathURL);
  		Log.logInfo("打开首页连接=" + Config.pathURL);
  		Log.logInfo("首页打开成功");
  	} catch (Exception e) {
  		Log.logInfo("首页打开失败");
  		Assert.fail("首页打开失败");
  	}
  	//因为首页的登录按钮首屏未显示，故使用JS方法先定位登录的按钮
     //选择的定位点为登录上面的一个资源位
  	WebElement scrollView = lp.getElement("location");
  	this.getIntoView(scrollView);
  	WebElement loginEntrance=lp.getElementNoWait("loginEntrance");
  	loginEntrance.click();
  	lp.getElement("userName").sendKeys("020230000003003");
  	lp.getElement("password").sendKeys("donkey1987");
  	lp.getElement("loginButton").click();
  	String expectedAlert="此账号不存在!";
  	WebElement alert=lp.getElement("wrongAlert");
  	if(alert.isDisplayed())
  	{
  		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
  				"登录名不存在-错误提示","登录密码错误");
  		if(alert.getText().contains(expectedAlert))
  		{
  		  Log.logInfo("= "+alert.getText());
  		  Log.logInfo("\"登录名不存在-错误提示\" 测试场景通过");
  		}
  		else{
  			Log.logInfo("\"登录名不存在-错误提示\"测试场景不通过");
  			Assert.fail("登录页面登录 - 登录名不存在提示");
  		}
  		Assert.assertTrue(true);
  	}
  	else{
  		Log.logInfo("\"登录名不存在-错误提示\"测试场景不通过");
  		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
  				"登录名不存在-错误提示","错误提示");
  		Assert.fail("登录页面登录 - 登录名不存在");
  	}
  }
	
//	String currentURL=driver.getCurrentUrl();
//	String actualURL="http://m.bl.com/h5-web/page/view_Index.html";
//	Log.logInfo("当前URL="+currentURL);
//	if(currentURL.equals(actualURL))
//	{
//		Log.logInfo("登录成功后页面跳转正常");
//		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
//				"登录成功","回首页");
//		Assert.assertTrue(true);
//	}
//	else{
//		Log.logInfo("登录成功后页面跳转异常");
//		screen.snapshot((TakesScreenshot) driver, timeString, "Login",
//				"登录成功","回首页失败");
//		Assert.fail("回首页失败");
//	}
	
}

//		TestExpect expect = blemall.loginBlemall(param);
//		expect.setClassName(this.getClass().getName());
//		expect.setMethodName(Thread.currentThread().getStackTrace()[1].getMethodName());
//		expect.setBussinessDesp("百联用户登录");
//		
//		expectedList.add(expect);


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


