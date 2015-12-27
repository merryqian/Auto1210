package com.blemall.smokeTest.testcases;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.blemall.smokeTest.page.OrderPage;
import com.test.base.TestBase;
import com.test.bean.Config;
import com.test.util.Log;
import com.test.util.ScreenShot;
import com.test.util.TimeString;

public class TestCase_CreateOrder extends TestBase{
	TimeString ts = new TimeString();
	String timeString = ts.getDate()  ;
	ScreenShot screen = new ScreenShot(driver);
	@Test
	public void defaultReceiver() throws IOException
	{
		try {
			this.goTo(Config.pathURL);
			Log.logInfo("打开连接=" + Config.pathURL);

		} catch (Exception e) {
			Log.logInfo("打开失败");
			Assert.fail("页面打开失败");
		}
		OrderPage op=new OrderPage(driver);
		
	}

}
