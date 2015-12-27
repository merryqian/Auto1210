package com.blemall.smokeTest.testcases;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.blemall.smokeTest.page.ShoppingCartPage;
import com.test.base.TestBase;
import com.test.bean.Config;
import com.test.util.Log;

public class TestCase_ShoppingCart extends TestBase {
	//将商品从“搜索列表页”加入购物车
	@Test
	public void addToCartFromSearch() throws IOException {
		try {
			this.goTo(Config.pathURL);
			Log.logInfo("打开连接=" + Config.pathURL);

		} catch (Exception e) {
			Log.logInfo("打开失败");
			Assert.fail("页面打开失败");
		}
		ShoppingCartPage sp = new ShoppingCartPage(driver);
		sp.getElement("search").click();
		//输入搜素关键字
		sp.getElement("keySearch").sendKeys("纯牛奶");
		sp.getElement("keySearch").sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			// sp.getElementNoWait("noGoods");
			//如果出现搜索无结果的提示
			driver.findElement(By.xpath(".//*[@id='goodslist']/div[2]"));
			Log.logInfo("该关键字未搜索出任何商品或者搜素引擎出现问题，请更换关键字搜索");
			Assert.fail("搜索失败");
		} catch (Exception e) {
			Log.logInfo("搜索成功");
			Assert.assertTrue(true);
		}
		boolean flag = true;
		int i = 1;
		while (flag) {
			//因只能显示部分的查询结果，但是如果都没有库存，则会导致用例执行失败
			//为了避免这种情况，所以取到商品名称后，用JS的方法，滚动显示到该行内容
			//直到加入购物车的按钮显示，循环终止
			String goodsXpath=".//*[@id='goodslist']/li["+i+"]/a/span[2]/span[1]";
			WebElement goodsName = driver.findElement(By
					.xpath(goodsXpath));
			this.getIntoView(goodsName);
			String buttonXpath=".//*[@id='goodslist']/li[" + i + "]/div/a";
			Log.logInfo("已比较的商品数 = "+i+" ");
			try{
				driver.findElement(By.xpath(buttonXpath)).isDisplayed();
				driver.findElement(By.xpath(buttonXpath)).click();
				WebElement success = sp.getElement("addSuccess");
				Log.logInfo("点击了加入购物车按钮后: " + success.getText());
				// 点击购物车后，应提示，加入购物车成功
				if (success.getText().contains("加入购物车成功")) 
				{
					// 输出加入购物车的商品名称
					Log.logInfo("加入购物车的商品= "+driver.findElement(By.xpath(".//*[@id='goodslist']/li[" + i
									+ "]/a/span[2]/span[1]")).getText());
					sp.getElement("fastBar").click();
					sp.getElement("shoppingcart").click();
					String result=sp.getElement("submitBtn").getText();
					String num=result.substring(4, 5);
					Log.logInfo("购物车中的商品数量= "+num);
					if(num.equals("1"))
					{
						Log.logInfo("加入购物车的商品数量与预期的相等");
						Assert.assertTrue(true);
						return;
					}
					else{
						Log.logInfo("加入购物车的商品数量与预期的不等");
						Assert.fail("加入购物车的商品数量与预期的不等");
					}
					
					
				} else {
					// 可能是加入失败，也有可能是弹出的提示信息修改
					Log.logInfo("未弹出加入购物车按钮");
					Assert.fail();
				      }
				//加入购物车按钮显示，终止循环
		  
		}catch(Exception e)
			{
			 //e.printStackTrace();
			Log.logInfo("继续查找下一个");
			 i++;
			}
		}

	}
	//将商品从“单品页”加入购物车
	@Test
	public void addToCartFromSinglePage() throws IOException {
		try {
			this.goTo(Config.pathURL);
			Log.logInfo("打开连接=" + Config.pathURL);

		} catch (Exception e) {
			Log.logInfo("打开失败");
			Assert.fail("页面打开失败");
		}
		ShoppingCartPage sp = new ShoppingCartPage(driver);
		sp.getElement("search").click();
		sp.getElement("keySearch").sendKeys("手机");
		sp.getElement("keySearch").sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			// sp.getElementNoWait("noGoods");
			driver.findElement(By.xpath(".//*[@id='goodslist']/div[2]"));
			Log.logInfo("该关键字未搜索出任何商品或者搜素引擎出现问题，请更换关键字搜索");
			Assert.fail("搜索失败");
		} catch (Exception e) {
			Log.logInfo("搜索成功");
			Assert.assertTrue(true);
		}
		boolean flag = true;
		int i = 1;
		while (flag) {
			String goodsXpath=".//*[@id='goodslist']/li["+i+"]/a/span[2]/span[1]";
			WebElement goodsName = driver.findElement(By
					.xpath(goodsXpath));
			this.getIntoView(goodsName);
			String xpath2=".//*[@id='goodslist']/li[" + i + "]/div/a";
			Log.logInfo("已比较的商品数 = "+i+" ");
			try{
				driver.findElement(By.xpath(xpath2)).isDisplayed();
				String gotoView=".//*[@id='goodslist']/li["+(i)+"]/a/span[2]/span[3]/span/span";
				WebElement gotoViewXpath=driver.findElement(By.xpath(gotoView));
				this.getIntoView(gotoViewXpath);
				goodsName.click();
				sp.getElement("addButton").click();
				sp.getElementNoWait("addButton").click();
				WebElement success = sp.getElement("singleAddSuccess");
				Log.logInfo("点击了加入购物车按钮后: " + success.getText());
				// 点击购物车后，应提示，加入购物车成功
				if (success.getText().contains("加入购物车成功")) 
				{
					sp.getElementNoWait("singleFastBar").click();
					sp.getElementNoWait("shoppingcartSingle").click();
					String result=sp.getElement("submitBtn").getText();
					String num=result.substring(4, 5);
					Log.logInfo("购物车中的商品数量= "+num);
					if(num.equals("1"))
					{
						Log.logInfo("加入购物车的商品数量与预期的相等");
						Assert.assertTrue(true);
						return;
					}
					else{
						Log.logInfo("加入购物车的商品数量与预期的不等");
						Assert.fail("加入购物车的商品数量与预期的不等");
					}
					
				} else {
					// 可能是加入失败，也有可能是弹出的提示信息修改
					Log.logInfo("未弹出加入购物车按钮");
					Assert.fail();
				      }
				//加入购物车按钮显示，终止循环
		  
		}catch(Exception e)
			{
			 //e.printStackTrace();
			Log.logInfo("继续查找下一个");
			 i++;
			}
		}

	}
	//单品页验证商品“库存不足”
	@Test
	public void noStockSinglePage() throws IOException {
		try {
			this.goTo(Config.pathURL);
			Log.logInfo("打开连接=" + Config.pathURL);

		} catch (Exception e) {
			Log.logInfo("打开失败");
			Assert.fail("页面打开失败");
		}
		ShoppingCartPage sp = new ShoppingCartPage(driver);
		sp.getElement("search").click();
		sp.getElement("keySearch").sendKeys("卡乐美");
		sp.getElement("keySearch").sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			// sp.getElementNoWait("noGoods");
			driver.findElement(By.xpath(".//*[@id='goodslist']/div[2]"));
			Log.logInfo("该关键字未搜索出任何商品或者搜素引擎出现问题，请更换关键字搜索");
			Assert.fail("搜索失败");
		} catch (Exception e) {
			Log.logInfo("搜索成功");
			Assert.assertTrue(true);
		}
		boolean flag = true;
		int i = 1;
		while (flag) {
			String goodsXpath=".//*[@id='goodslist']/li["+i+"]/a/span[2]/span[1]";
			WebElement goodsName = driver.findElement(By
					.xpath(goodsXpath));
			this.getIntoView(goodsName);
			String xpath2=".//*[@id='goodslist']/li["+i+"]/a/span[1]/span";
			Log.logInfo("已比较的商品数 = "+i+" ");
			try{
				driver.findElement(By.xpath(xpath2)).isDisplayed();
				
				Log.logInfo("查找到第 "+i+"个时，无货的样式展示");
		  
		    }catch(Exception e){
		    	Log.logInfo("继续查找下一个");
				 i++;
		      }
		    }

	   }
}
