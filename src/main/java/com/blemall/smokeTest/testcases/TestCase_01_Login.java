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
import com.blemall.smokeTest.page.ShoppingCartPage;
import com.test.base.TestBase;
import com.test.bean.Config;
import com.test.bean.TestExpect;
import com.test.bean.TestngListener;
import com.test.util.ExcelReader;
import com.test.util.Log;
import com.test.util.ScreenShot;
import com.test.util.TimeString;

//@Listeners(TestngListener.class)
public class TestCase_01_Login extends TestBase {
	TimeString ts = new TimeString();
	String timeString = ts.getDate();
	ScreenShot screen = new ScreenShot(driver);

	// 登录页面的UI显示 - 百联logo
	@Test(dataProvider = "providerMethod")
	public void loginH5Logo(Map<String, String> param) throws IOException,
			InterruptedException {
		LoginPage lp = new LoginPage(driver);
		// 打开H5
		this.openURL();
		// 进入登录页面，检查logo
		this.logoCheck(lp, param);
	}

	// 登录页面的UI显示 - 用户名输入框
	@Test(dataProvider = "providerMethod")
	public void userNameDefaultText(Map<String, String> param) throws IOException, InterruptedException {
		LoginPage lp = new LoginPage(driver);
		// 打开H5
		this.openURL();
		// 进入登录页面，检查logo
		this.userNameDefaultCheck(lp, param);
	}

	// 登录页面的UI显示 - 密码输入框
	@Test(dataProvider = "providerMethod")
	public void userPasswordDefaultText(Map<String, String> param) throws IOException,InterruptedException {
		LoginPage lp = new LoginPage(driver);
		// 打开H5
		this.openURL();
		// 进入登录页面，检查logo
		this.userPasswordDefaultCheck(lp, param);

	}

	// 登录页面的UI显示 - 密码输入框
	@Test(dataProvider = "providerMethod")
	public void forgetPasswordLink(Map<String, String> param)throws IOException, InterruptedException {
		LoginPage lp = new LoginPage(driver);
		// 打开H5
		this.openURL();
		// 进入登录页面
		this.gotoLoginPage(lp);
		//检查忘记密码链接
		this.forgetPasswordLinkCheck(lp, param);
	}


	// 登录页面免费注册显示及链接
	@Test(dataProvider = "providerMethod")
	public void freeRegisterLink(Map<String, String> param) throws IOException,InterruptedException {
		LoginPage lp = new LoginPage(driver);
		// 打开H5
		this.openURL();
		// 进入登录页面
		this.gotoLoginPage(lp);
		//检查免费注册链接
		this.freeRegisterLinkCheck(lp, param);
		
	}

	

	// 登录页面的UI显示 - “百联通会员卡首次登录”链接
	@Test(dataProvider = "providerMethod")
	public void solidCardLoginLink(Map<String, String> param)throws IOException, InterruptedException {
		LoginPage lp = new LoginPage(driver);
		// 打开H5
		this.openURL();
		// 进入登录页面
		this.gotoLoginPage(lp);
		//检查“百联通会员卡首次登录”链接
		this.solidCardLoginLinkCheck(lp, param);
	}


	// 登录页面登录 - 密码错误提示
	@Test(dataProvider = "providerMethod")
	public void wrongPasswordAlert(Map<String, String> param)throws IOException, InterruptedException {
		LoginPage lp = new LoginPage(driver);
		this.openURL();
		// 进入登录页面
		this.gotoLoginPage(lp);
		//检查密码错误提示
		this.wrongPasswordAlertCheck(lp, param);
	}



	// 登录名不存在-错误提示
	@Test(dataProvider = "providerMethod")
	public void usernameNotExist(Map<String, String> param) throws IOException,InterruptedException {
		LoginPage lp = new LoginPage(driver);
		this.openURL();
		// 进入登录页面
		this.gotoLoginPage(lp);
		//检查登录名不存在
		this.usernameNotExistCheck(lp, param);
	}


	// 登录页面登录 - 用户名和密码正确
	@Test(dataProvider = "providerMethod")
	public void loginSuccess(Map<String, String> param) throws IOException,InterruptedException {
		LoginPage lp = new LoginPage(driver);
		this.openURL();
		// 进入登录页面
		this.gotoLoginPage(lp);
		//检查登录成功
		this.loginSuccessCheck(lp, param);
	
	}


	// 登录页面登录-实体卡登录
	@Test(dataProvider = "providerMethod")
	public void solidCardLogin(Map<String, String> param) throws IOException,InterruptedException {
		LoginPage lp = new LoginPage(driver);
		this.openURL();
		// 进入登录页面
		this.gotoLoginPage(lp);
		//检查登录成功
		this.solidCardLoginCheck(lp, param);
	}


	// 打开H5首页
	public void openURL() {
		try {
			this.goTo(Config.pathURL);
			Log.logInfo("打开首页连接=" + Config.pathURL);
			wait(3000);
			String log = ".//*[@id='main']/div/header/a[3]/img";
			if (isElementExists(log)) {
				Log.logInfo("首页打开成功");
			} else {
				Log.logInfo("首页打开失败");
				return;
			}

		} catch (Exception e) {
			Log.logInfo("[首页]  打开页面失败");
			Assert.fail("[首页]  打开页面失败");
		}
	}

	// 判断某个元素是否存在
	private boolean isElementExists(String path) {
		boolean result = false;
		try {
			driver.findElement(By.xpath(path));
			result = true;
		} catch (Exception e) {
			Log.logInfo("元素不存在,path=" + path);
		}

		return result;
	}

	// 登录
	public void login(LoginPage lp, String username, String password) {
		// 定位到首页的登录按钮位置
		WebElement scrollView = lp.getElement("location");
		this.getIntoView(scrollView);
		Log.logInfo("定位到首页的登录按钮位置");
		WebElement loginEntrance = lp.getElementNoWait("loginEntrance");
		loginEntrance.click();
		Log.logInfo("点击首页的登录按钮入口");
		lp.getElement("userName").sendKeys(username);
		Log.logInfo("用户名=" + username);
		lp.getElement("password").sendKeys(password);
		Log.logInfo("密码=" + password);
		lp.getElementNoWait("loginButton").click();
		Log.logInfo("点击登录按钮");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			driver.findElement(By.linkText("登录"));
			Log.logInfo("登录失败");
			Assert.fail("登录失败");
		} catch (Exception e) {
			Log.logInfo("登录成功后跳转回首页");
			Assert.assertTrue(true);
		}
	}

	// 检查登录页面的百联logo
	private void logoCheck(LoginPage lp, Map<String, String> param) {
		// 因为首页的登录按钮首屏未显示，故使用JS方法先定位登录的按钮
		// 选择的定位点为登录上面的一个资源位
		WebElement scrollView = lp.getElement("location");
		this.getIntoView(scrollView);
		// 点击登录按钮
		WebElement loginEntrance = lp.getElementNoWait("loginEntrance");
		loginEntrance.click();
		Log.logInfo("点击登录按钮");
		// 获取预期的图片logo地址
		String logoExpected = param.get("logoExpected");
		// 判断登录页面的图片地址是否是预期的地址，如果不对也有可能是更换了图片地址
		WebElement logo = lp.getElement("logoInLoginPage");
		if (logo.getAttribute("src").equals(logoExpected)) {
			Log.logInfo("logo图片URL= " + logo.getAttribute("src"));
			Log.logInfo("\"登录页面的UI显示 - 百联logo\" 测试场景通过");
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录页面的UI显示 - 百联logo", "检查通过");
		} else {
			Log.logInfo("\"登录页面的UI显示 - 百联logo\" 测试场景不通过");
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录页面的UI显示 - 百联logo", "检查不通过");
			Assert.fail("登录页面的logo图片地址不对，或者其他原因，请具体");
		}
	}
	//检查用户名框默认显示
	private void userNameDefaultCheck(LoginPage lp, Map<String, String> param) {
		// 因为首页的登录按钮首屏未显示，故使用JS方法先定位登录的按钮
		// 选择的定位点为登录上面的一个资源位
		WebElement scrollView = lp.getElement("location");
		this.getIntoView(scrollView);
		WebElement loginEntrance = lp.getElementNoWait("loginEntrance");
		loginEntrance.click();
		Log.logInfo("点击登录按钮");
		WebElement username = lp.getElement("userName");
		if (username.isDisplayed()) {
			String actualText = username.getAttribute("placeholder");
			String expectedText = param.get("expectText");
			Log.logInfo("用户名输入框中默认预期显示=" + expectedText);
			Log.logInfo("用户名输入框中默认实际显示=" + actualText);
			if (actualText.contains(expectedText)) {
				Log.logInfo("\"登录页面的UI显示 - 用户名输入框\" 测试场景通过");
				screen.snapshot((TakesScreenshot) driver, timeString, "Login",
						"登录页面的UI显示 - 用户名输入框", "检查用户名输入框");
				Assert.assertTrue(true);
			} else {
				Log.logInfo("用户名输入框中默认显示文字显示异常");
				screen.snapshot((TakesScreenshot) driver, timeString, "Login",
						"登录页面的UI显示 - 用户名输入框", "用户名输入框中默认显示文字显示异常");
				Assert.fail();
			}
		} else {
			Log.logInfo("登录页面打开异常");
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录页面的UI显示 - 用户名输入框", "H5显示异常");
			Assert.fail("登录页面打开异常");
		}
	}
//检查密码框默认显示
	private void userPasswordDefaultCheck(LoginPage lp,
			Map<String, String> param) {
		// 因为首页的登录按钮首屏未显示，故使用JS方法先定位登录的按钮
		// 选择的定位点为登录上面的一个资源位
		WebElement scrollView = lp.getElement("location");
		this.getIntoView(scrollView);
		WebElement loginEntrance = lp.getElementNoWait("loginEntrance");
		loginEntrance.click();
		WebElement password = lp.getElement("password");
		if (password.isDisplayed()) {
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录页面的UI显示 - 密码输入框", "打开登录页面");
			String actualText = password.getAttribute("placeholder");
			String expectedText = param.get("expectedText");
			if (actualText.equals(expectedText)) {
				Log.logInfo("密码输入框中默认预期显示=" + expectedText);
				Log.logInfo("密码输入框中默认实际显示=" + actualText);
				Log.logInfo("\"登录页面的UI显示 - 密码输入框\" 测试场景通过");
				screen.snapshot((TakesScreenshot) driver, timeString, "Login",
						"登录页面的UI显示 - 密码输入框", "检查密码输入框");
				Assert.assertTrue(true);
			} else {
				Log.logInfo("密码输入框中默认显示文字显示异常");
				screen.snapshot((TakesScreenshot) driver, timeString, "Login",
						"登录页面的UI显示 - 密码输入框", "密码输入框中默认显示文字显示异常");
				Assert.fail();
			}
		} else {
			Log.logInfo("登录页面打开异常");
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录页面的UI显示 - 密码输入框", "H5登录页面打开异常");
			Assert.fail("登录页面打开异常");
		}
	}

	// 检查忘记密码链接
	private void forgetPasswordLinkCheck(LoginPage lp, Map<String, String> param) {
		WebElement forgetLink = lp.getElement("forgetPassword");
		String actualText = forgetLink.getAttribute("href");
		String expectedText = param.get("expectedText");
		Log.logInfo("密码输入框中默认预期显示=" + expectedText);
		Log.logInfo("密码输入框中默认实际显示=" + actualText);
		if (forgetLink.getText().equals("忘记密码？")) {
			// 点击忘记密码链接
			forgetLink.click();
			wait(3000);
			Log.logInfo("title= " + driver.getTitle());
			Log.logInfo("\"登录页面的UI显示 - “忘记密码”链接\" 测试场景通过");
			if (lp.getElement("forgetPasswordText").isDisplayed()) {
				Log.logInfo("忘记密码链接打开正常");
				screen.snapshot((TakesScreenshot) driver, timeString, "Login",
						"登录页面忘记密码链接", "忘记密码链接打开正常");
				Assert.assertTrue(true);
			} else {
				Log.logInfo("忘记密码链接打开异常");
				screen.snapshot((TakesScreenshot) driver, timeString, "Login",
						"登录页面忘记密码链接", "忘记密码链接打开异常");
				Assert.fail("忘记密码链接打开异常");
			}

		} else {
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录页面忘记密码链接", "忘记密码链接异常");
			Assert.fail();
		}
	}

	private void gotoLoginPage(LoginPage lp) {
		// 因为首页的登录按钮首屏未显示，故使用JS方法先定位登录的按钮
		// 选择的定位点为登录上面的一个资源位
		WebElement scrollView = lp.getElement("location");
		this.getIntoView(scrollView);
		WebElement loginEntrance = lp.getElementNoWait("loginEntrance");
		loginEntrance.click();
		Log.logInfo("点击首页的登录按钮");
	}

	private void freeRegisterLinkCheck(LoginPage lp, Map<String, String> param) {
		WebElement freeRegister = lp.getElement("freeRegister");
		// 获取免费注册的链接
		String actualURL = freeRegister.getAttribute("href");
		String expectedURL = param.get("expectedURL");
		Log.logInfo("登录页面展示预期免费注册URL=" + expectedURL);
		Log.logInfo("登录页面展示实际免费注册URL=" + actualURL);
		if ((actualURL.equals(expectedURL))) {
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录页面免费注册链接", "1");
			// 点击免费注册链接
			freeRegister.click();
			wait(3000);
			if (driver.getTitle().equals(param.get("title"))) {
				Log.logInfo("\"登录页面的UI显示 - “免费注册”链接\" 测试场景通过");
				screen.snapshot((TakesScreenshot) driver, timeString, "Login",
						"登录页面免费注册链接", "免费注册链接打开正常");
				Assert.assertTrue(true);
			} else {
				Log.logInfo("免费注册链接打开异常");
				screen.snapshot((TakesScreenshot) driver, timeString, "Login",
						"登录页面免费注册链接", "免费注册链接打开异常");
				Assert.fail("免费注册链接打开异常");
			}

		}

	}

	// 检查百联通会员卡首次登录
	private void solidCardLoginLinkCheck(LoginPage lp, Map<String, String> param) {
		WebElement solidButton = lp.getElement("solidButton");
		this.getIntoView(solidButton);
		// 获取文字显示
		String actualText = solidButton.getText();
		String expectText = param.get("expectedText");
		// 获取链接
		Log.logInfo("百联通会员卡预期显示=" + expectText);
		Log.logInfo("百联通会员卡实际显示=" + actualText);
		if (actualText.equals(expectText)) {
			solidButton.click();
			wait(3000);
			if (driver.getTitle().equals(param.get("title"))) {
				Log.logInfo("\"登录页面的UI显示 - “百联通会员卡首次登录”链接\" 测试场景通过");
				Log.logInfo("实体卡首次登录链接打开正常");
				screen.snapshot((TakesScreenshot) driver, timeString, "Login",
						"登录页面百联通会员卡首次登录链接", "百联通会员卡首次登录链接打开正常");
				Assert.assertTrue(true);
			} else {
				Log.logInfo("百联通会员卡首次登录链接打开异常");
				screen.snapshot((TakesScreenshot) driver, timeString, "Login",
						"登录页面百联通会员卡首次登录链接", "百联通会员卡首次登录链接打开异常");
				Assert.fail("百联通会员卡首次登录链接打开异常");
			}

		} else {
			Log.logInfo("实体卡登录没看到吗？");
		}
	}
//密码错误提示
	private void wrongPasswordAlertCheck(LoginPage lp, Map<String, String> param) {
		lp.getElement("userName").sendKeys(param.get("username"));
		lp.getElement("password").sendKeys(param.get("password"));
		lp.getElement("loginButton").click();
		String expectedAlert = param.get("expectedAlert");
		WebElement alert = lp.getElement("wrongAlert");
		Log.logInfo("预期提示=" + expectedAlert);
		Log.logInfo("实际提示=" + alert.getText());
		if (alert.getText().contains(expectedAlert)) {
			Log.logInfo("密码错误提示= " + alert.getText());
			Log.logInfo("登录页面登录 - 密码错误提示 测试场景通过");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("登录页面登录 - 密码错误提示 测试场景不通过");
			Assert.fail("登录页面登录 - 密码错误提示");
		}

	}
//用户名不存在
	private void usernameNotExistCheck(LoginPage lp, Map<String, String> param) {
		lp.getElement("userName").sendKeys(param.get("username"));
		lp.getElement("password").sendKeys(param.get("password"));
		lp.getElement("loginButton").click();
		String expectedAlert = param.get("expectedAlert");
		WebElement alert = lp.getElement("wrongAlert");
		Log.logInfo("用户名不存在预期提示信息= " + expectedAlert);
		Log.logInfo("用户名不存在预期提示信息= " + alert.getText());
		if (alert.isDisplayed()) {
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录名不存在-错误提示", "登录密码错误");
			if (alert.getText().contains(expectedAlert)) {
				;
				Log.logInfo("\"登录名不存在-错误提示\" 测试场景通过");
			} else {
				Log.logInfo("\"登录名不存在-错误提示\"测试场景不通过");
				Assert.fail("登录页面登录 - 登录名不存在提示");
			}
			Assert.assertTrue(true);
		} else {
			Log.logInfo("\"登录名不存在-错误提示\"测试场景不通过");
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录名不存在-错误提示", "错误提示");
			Assert.fail("登录页面登录 - 登录名不存在");
		}
	}
//登录成功
	private void loginSuccessCheck(LoginPage lp, Map<String, String> param) {
		lp.getElement("userName").sendKeys(param.get("username"));
		lp.getElement("password").sendKeys(param.get("password"));
		screen.snapshot((TakesScreenshot) driver, timeString, "Login", "登录成功",
				"登录页面输入用户名、密码");
		lp.getElementNoWait("loginButton").click();
		wait(3000);
		try {
			driver.findElement(By.linkText("登录"));
			Log.logInfo("登录失败");
			Assert.fail("登录失败");
		} catch (Exception e) {
			Log.logInfo("登录成功");
			Assert.assertTrue(true);
		}		
	}

	// 百联通会员卡登录
	private void solidCardLoginCheck(LoginPage lp, Map<String, String> param) {
//		WebElement solidButton = lp.getElement("solidButton");
//		this.getIntoView(solidButton);
//		solidButton.click();
		lp.getElement("userName").sendKeys(param.get("username"));
		lp.getElement("password").sendKeys(param.get("password"));
		lp.getElement("loginButton").click();
		wait(3000);
		String getCurrentURL = driver.getCurrentUrl();
		String expectedURL = param.get("expectURL");
		Log.logInfo("currentURL=" + getCurrentURL);
		if (getCurrentURL.equals(expectedURL)) {
			Log.logInfo("登录成功");
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录成功", "实体卡登录");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("登录失败");
			screen.snapshot((TakesScreenshot) driver, timeString, "Login",
					"登录失败", "实体卡登录");
			Assert.fail("实体卡登录失败");
		}
	}
}

// TestExpect expect = blemall.loginBlemall(param);
// expect.setClassName(this.getClass().getName());
// expect.setMethodName(Thread.currentThread().getStackTrace()[1].getMethodName());
// expect.setBussinessDesp("百联用户登录");
//
// expectedList.add(expect);

// @Test
// public void testLogin() throws InterruptedException{
// TestExpect expect = new TestExpect();
// expect.setExpect("测试期望�?");
// expect.setActual("真实期望�?");
// expect.setClassName(this.getClass().getName());
// expect.setMethodName(Thread.currentThread().getStackTrace()[1].getMethodName());
// expect.setBussinessDesp("这个是一个简单的测试");
// Thread.sleep(5000);
// expectedList.add(expect);
// }

