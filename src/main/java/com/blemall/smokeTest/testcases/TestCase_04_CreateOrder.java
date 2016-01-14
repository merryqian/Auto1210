package com.blemall.smokeTest.testcases;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.blemall.smokeTest.page.OrderPage;
import com.blemall.smokeTest.page.ToPayOrderPage;
import com.test.base.TestBase;
import com.test.bean.Config;
import com.test.util.Log;
import com.test.util.ScreenShot;
import com.test.util.TimeString;

public class TestCase_04_CreateOrder extends TestBase{
	TimeString ts = new TimeString();
	String timeString = ts.getDate()  ;
	ScreenShot screen = new ScreenShot(driver);
	
	public void login(ToPayOrderPage tp, String username,String password)
	{
		WebElement scrollView = tp.getElement("location");
		this.getIntoView(scrollView);
		WebElement loginEntrance=tp.getElementNoWait("loginEntrance");
		loginEntrance.click();
		tp.getElement("userName").sendKeys(username);
		tp.getElement("password").sendKeys(password);
		tp.getElementNoWait("loginButton").click();
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
	public void gotoShoppingCart(ToPayOrderPage tp)
	{
		tp.getElement("shoppingCart").click();
		tp.getElement("submitBtn").click();
	}
	// 判断某个元素是否存在
		@SuppressWarnings("unused")
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

      //打开首页
		public void openURL() {
			try {
				this.goTo(Config.pathURL);
			} catch (Exception e) {
				Log.logInfo("[购物车]  打开页面失败");
				Assert.fail("[购物车]  打开页面失败");
			}
		}
		//检查默认收货地址图标
		public void checkDefaultAddress(ToPayOrderPage tp)
		{
			String defaultText=tp.getElement("defaultAddress").getText();
			if(defaultText.equals("默认"))
			{
				Log.logInfo("校验默认收货人的图标样式 测试场景通过");
				screen.snapshot((TakesScreenshot) driver, timeString, "订单确认页面",
						"订单确认页面","默认收货地址图标检查");
				Assert.assertTrue(true);
				
			}
			else{
				Log.logInfo("校验默认收货人的图标样式 测试场景不通过");
				Assert.fail();
			}
					
		}
		//检查无收货地址展示
		public void checkNoAddress(ToPayOrderPage tp)
		{
			String noAddress=tp.getElement("reciverInfo").getText();
			if(noAddress.equals("请选择收货地址"))
			{
				Log.logInfo("检验无收获地址显示 测试场景通过");
				screen.snapshot((TakesScreenshot) driver, timeString, "订单确认页面",
						"订单确认页面","检验无收获地址显示");
				Assert.assertTrue(true);
				
			}
			else{
				Log.logInfo("检验无收获地址显示 测试场景不通过");
				Assert.fail();
			}
		}
		//新增收货地址方法
		public void addressAdd(ToPayOrderPage tp,Map<String,String> param)
		{
			tp.getElement("addressLink").click();
			wait(3000);
			tp.getElement("addressAdd").click();
			wait(3000);
			tp.getElement("reciverName").sendKeys(param.get("reciverName"));
			tp.getElement("reciverPhone").sendKeys(param.get("reciverPhone"));
			//tp.getElement("provinceId").click();
			wait(3000);
			this.getValueFromSelect(tp, "provinceId",param.get("provinceId"));
			this.getValueFromSelect(tp, "cityId",param.get("cityId"));
			this.getValueFromSelect(tp, "districtId",param.get("districtId"));
			tp.getElement("address").sendKeys(param.get("address"));
   		    tp.getElement("addressSubmit").click();
   		   
		}
		public void getIntoView(WebElement el)
		{
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].scrollIntoView();", el);
		}
		public void isAddSuccess(ToPayOrderPage tp,Map<String,String> param)
		{
			this.getIntoView(tp.getElement("reciverInfo"));
			String receiver=tp.getElement("receiverNameText").getText();
	        String phone=tp.getElement("lab_orderreceiverMphone").getText();
	        Log.logInfo("收件人= "+receiver);
	        Log.logInfo("手机= "+phone);
	        if(receiver.equals(param.get("reciverName"))&&phone.equals(param.get("reciverPhone")))
	        {
	        	Log.logInfo("校验新增收货地址 测试场景通过");
	        	Assert.assertTrue(true);
	        }
	        else{
	        	Log.logInfo("校验新增收货地址 测试场景不通过");
	        	Assert.fail();
	        }
		}
		public void getValueFromSelect(ToPayOrderPage tp,String xpath,String value)
		{
			Select select=new Select(tp.getElementNoWait(xpath));
			select.selectByVisibleText(value);
		}
	//校验默认收货人的图标样式
	@Test(dataProvider="providerMethod")
	public void defaultReceiverCheck(Map<String,String> param) throws IOException
	{
	   ToPayOrderPage tp=new ToPayOrderPage(driver);
		//调用打开首页方法
		this.openURL();
		//调用登录方法
		this.login(tp,param.get("username"), param.get("password"));
		//调用打开购物车方法
		this.gotoShoppingCart(tp);
		//调用检查默认收货地址方法
		this.checkDefaultAddress(tp);
	}
	
	//检验无收获地址显示
	@Test(dataProvider="providerMethod")
	public void noAddressCheck(Map<String,String> param) throws IOException
	{
	   ToPayOrderPage tp=new ToPayOrderPage(driver);
		//调用打开首页方法
		this.openURL();
		//调用登录方法
		this.login(tp,param.get("username"), param.get("password"));
		//调用打开购物车方法
		this.gotoShoppingCart(tp);
		//调用检查默认收货地址方法
		this.checkNoAddress(tp);
	}
	//校验新增默认收货地址
	@Test(dataProvider="providerMethod")
	public void addAddress(Map<String,String> param) throws IOException, InterruptedException
	{
	   ToPayOrderPage tp=new ToPayOrderPage(driver);
		//调用打开首页方法
		this.openURL();
		//调用登录方法
		this.login(tp,param.get("username"), param.get("password"));
		//调用打开购物车方法
		this.gotoShoppingCart(tp);
		//调用增加收货地址方法
		this.addressAdd(tp,param);
		 this.isAddSuccess(tp,param);
	}


}
