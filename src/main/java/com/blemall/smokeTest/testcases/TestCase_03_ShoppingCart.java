package com.blemall.smokeTest.testcases;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class TestCase_03_ShoppingCart extends TestBase {
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

	private boolean isElementExists(String path, WebElement scrollPath) {
		boolean result = false;

		try {
			this.getIntoView(scrollPath);
			driver.findElement(By.xpath(path));
			result = true;
		} catch (Exception e) {
			Log.logInfo("元素不存在,path=" + path);
		}

		return result;
	}

	public void openURL() {
		try {
			this.goTo(Config.pathURL);
		} catch (Exception e) {
			Log.logInfo("[购物车]  打开页面失败");
			Assert.fail("[购物车]  打开页面失败");
		}
	}

	// 查找商品
	private ShoppingCartPage searchKey(String key) {
		ShoppingCartPage sp = null;
		try {
			sp = new ShoppingCartPage(driver);
			sp.getElement("search").click();
			sp.getElement("keySearch").sendKeys(key);
			sp.getElement("keySearch").sendKeys(Keys.ENTER);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (IOException e) {
			e.printStackTrace();
			Log.logError("[搜索] 搜索发生异常，异常信息为：" + e.getMessage() + " key=" + key);
		}

		return sp;
	}

	// 将商品从“搜索列表页”加入购物车
	@Test(dataProvider = "providerMethod")
	public void addToCartFromSearch(Map<String, String> param)
			throws IOException {
		// 打开H5首页
		openURL();
		// 调用搜索方法
		ShoppingCartPage sp = searchKey(param.get("searchKey"));
		// 判断搜索出来的结果是否为空
		String noGoodsPath = ".//*[@id='goodslist']/div[2]";
		if (isElementExists(noGoodsPath)) {
			Assert.fail("未搜索到任何商品");
		}
		// 查找搜索结果，直到找到有库存的商品
		String buttonXpath = findStockGoods();
		// 调用加入购物车按钮
		addToShoppingCartFromList(sp, buttonXpath);
	}

	private String findStockGoods() {

		int i = 1;
		while (true) {
			String cartItemPath = ".//*[@id='goodslist']/li[" + i + "]/div/a";
			String goodsXpath = ".//*[@id='goodslist']/li[" + i
					+ "]/a/span[2]/span[1]";
			WebElement goodsName = driver.findElement(By.xpath(goodsXpath));
			if (!isElementExists(cartItemPath, goodsName)) {
				i++;
				Log.logInfo("已比较的商品数 = " + i + " ");
				continue;
			} else {
				String buttonXpath = ".//*[@id='goodslist']/li[" + i
						+ "]/div/a";
				return buttonXpath;
			}
		}
	}

	// 调用加入购物车按钮
	public void addToShoppingCartFromList(ShoppingCartPage sp, String xpath) {
		WebElement el = driver.findElement(By.xpath(xpath));
		el.click();
		WebElement success = sp.getElement("addSuccess");
		// 点击购物车后，应提示，加入购物车成功
		if (success.getText().contains("加入购物车成功")) {
			// 输出加入购物车的商品名称
			sp.getElement("fastBar").click();
			sp.getElement("shoppingcart").click();
			String result = sp.getElement("submitBtn").getText();
			String num = result.substring(4, 5);
			Log.logInfo("购物车中的商品数量= " + num);
			if (num.equals("1")) {
				Log.logInfo("加入购物车的商品数量与预期的相等");
				Assert.assertTrue(true);
				return;
			} else {
				Log.logInfo("加入购物车的商品数量与预期的不等");
				Assert.fail("加入购物车的商品数量与预期的不等");
			}

		} else {
			// 可能是加入失败，也有可能是弹出的提示信息修改
			Log.logInfo("未弹出加入购物车按钮");
			Assert.fail();
		}

	}

	@Test(dataProvider = "providerMethod")
	public void addToShopCart(Map<String, String> param) {
		// 打开H5首页
		openURL();
		// 调用搜索方法
		ShoppingCartPage sp = searchKey(param.get("searchKey"));
		// 判断搜索出来的结果是否为空
		String noGoodsPath = ".//*[@id='goodslist']/div[2]";
		if (isElementExists(noGoodsPath)) {
			Assert.fail("未搜索到任何商品");
		}
		// 查找有加入购物车按钮的商品
		goToSinglePage(sp);
		// 调用加入购物车的方法
		addToShoppingCartFromSingle(sp);

	}

	// 查找有加入购物车按钮的商品，并跳转到商品详情页
	private void goToSinglePage(ShoppingCartPage sp) {
		int i = 1;
		while (true) {
			String cartItemPath = ".//*[@id='goodslist']/li[" + i + "]/div/a";
			String goodsXpath = ".//*[@id='goodslist']/li[" + i
					+ "]/a/span[2]/span[1]";
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement goodsName = driver.findElement(By.xpath(goodsXpath));
			if (!isElementExists(cartItemPath, goodsName)) {
				i++;
				Log.logInfo("已比较的商品数 = " + i + " ");
				continue;
			} else {
				Log.logInfo("比较了" + i + " 次，找到了有库存的商品");
				String goodsPrice = ".//*[@id='goodslist']/li[" + i
						+ "]/a/span[2]/span[3]/span/span";
				driver.findElement(By.xpath(goodsPrice)).click();
				break;
			}
		}
	}

	// 进入单品页，加入购物车并判断是否加入成功
	private void addToShoppingCartFromSingle(ShoppingCartPage sp) {

		sp.getElement("addButton").click();
		sp.getElementNoWait("addButton").click();
		WebElement success = sp.getElement("singleAddSuccess");
		Log.logInfo("点击了加入购物车按钮后: " + success.getText());
		// 点击购物车后，应提示，加入购物车成功
		if (success.getText().contains("加入购物车成功")) {
			sp.getElementNoWait("singleFastBar").click();
			sp.getElementNoWait("shoppingcartSingle").click();
			String result = sp.getElement("submitBtn").getText();
			String num = result.substring(4, 5);
			Log.logInfo("购物车中的商品数量= " + num);
			if (num.equals("1")) {
				Log.logInfo("加入购物车的商品数量与预期的相等");
				Assert.assertTrue(true);
				return;
			} else {
				Log.logInfo("加入购物车的商品数量与预期的不等");
				Assert.fail("加入购物车的商品数量与预期的不等");
			}

		} else {
			// 可能是加入失败，也有可能是弹出的提示信息修改
			Log.logInfo("未弹出加入购物车按钮");
			Assert.fail();
		}
	}

	// 单品页验证商品“库存不足”
	@Test(dataProvider = "providerMethod")
	public void noStockSinglePageConfirm(Map<String, String> param) {
		// 打开H5首页
		openURL();
		// 调用搜索方法
		ShoppingCartPage sp = searchKey(param.get("searchKey"));
		// 判断搜索出来的结果是否为空
		String noGoodsPath = ".//*[@id='goodslist']/div[2]";
		if (isElementExists(noGoodsPath)) {
			Assert.fail("未搜索到任何商品");
		}
		// 查找搜索结果，直到找到没有库存的商品
		findNoStockGoods();
		// 无货检查
		noStockCheck(sp);

	}

	private void findNoStockGoods() {
		int i = 1;
		while (true) {
			String goodsXpath = ".//*[@id='goodslist']/li[" + i+ "]/a/span[2]/span[1]";
			String goodsPrice = ".//*[@id='goodslist']/li[" + i+ "]/a/span[2]/span[3]/span/span";
			WebElement goodsNameXpath = driver.findElement(By.xpath(goodsXpath));

			String cartItemPath = ".//*[@id='goodslist']/li[" + i+ "]/a/span[1]/span";
			// 查找无货商品样式显示
			if (isElementExists(cartItemPath, goodsNameXpath)) {
				Log.logInfo("比较了" + i + " 次，找到了没有库存的商品");

				driver.findElement(By.xpath(goodsPrice)).click();
				break;
			} else {
				i++;
				continue;
			}

		}
	}

	// 无货检查
	public void noStockCheck(ShoppingCartPage sp) {
		sp.getElement("addButton").click();

		String noGoods = ".//*[@id='productStor']";
		if (!isElementExists(noGoods)) {
			Assert.fail("打开的商品未显示“已售罄”");
		} else {
			Log.logInfo(sp.getElement("noStock").getText());
			sp.getElementNoWait("addButton").click();
			WebElement fail = sp.getElement("noGoods");
			Log.logInfo("点击确定后: " + fail.getText());
			if (fail.getText().contains("库存不足")) {
				Log.logInfo("单品页验证商品“库存不足” 测试用例通过");
				Assert.assertTrue(true);
			} else {
				Log.logInfo("单品页验证商品“库存不足” 测试用例通过");
				Assert.fail("提示信息不对或者其他原因导致失败");
			}

		}
	}

	// 闪购商品展示
	@Test(dataProvider = "providerMethod")
	public void goodsShanGou(Map<String, String> param) throws IOException,
			InterruptedException {
		// 打开H5首页
		openURL();
		// 进入购物车页面
		ShoppingCartPage sp = new ShoppingCartPage(driver);
		sp.getElement("shoppingcartInFirstPage").click();
		// 判断未登录的购物车的样式展示
		String notLoginText = sp.getElement("loginNow").getText();
		Log.logInfo("未登录= " + notLoginText);
		sp.getElement("loginNow").click();
		sp.getElement("username").sendKeys(param.get("username"));
		sp.getElement("password").sendKeys(param.get("password"));
		sp.getElement("submitButton").click();
		String shanGouNum = "//i[contains(text(),'闪购')]";
		// 查找已下架商品，购物车中没有找到闪购的，则判断用例失败
		if (isElementExists(shanGouNum)) {
			int canNotBuyNum = sp.getElements("shanGouNum").size();
			Log.logInfo("闪购商品的数量=" + canNotBuyNum);
			Log.logInfo("购物车中没有闪购活动的商品，请重新检查");
			Log.logInfo("购物车闪购商品标签展示 测试场景不通过");
			Assert.assertTrue(true);

		} else {
			Log.logInfo("购物车闪购商品标签展示 测试场景不通过");
			Assert.fail("购物车没有闪购活动的商品，请重新检查");
		}

	}

	// 已下架商品展示
	@Test(dataProvider = "providerMethod")
	public void goodsXiaJia(Map<String, String> param) throws IOException,
			InterruptedException {
		// 打开H5首页
		openURL();
		// 进入购物车页面
		ShoppingCartPage sp = new ShoppingCartPage(driver);
		sp.getElement("shoppingcartInFirstPage").click();
		// 判断未登录的购物车的样式展示
		String notLoginText = sp.getElement("loginNow").getText();
		Log.logInfo("未登录= " + notLoginText);
		sp.getElement("loginNow").click();
		sp.getElement("username").sendKeys(param.get("username"));
		sp.getElement("password").sendKeys(param.get("password"));
		sp.getElement("submitButton").click();
		String xiaJiaNum = "//div[contains(text(),'已下架')]";
		// 查找已下架商品，购物车中没有找到已下架字样的，则判断用例失败
		if (isElementExists(xiaJiaNum)) {
			int canNotBuyNum = sp.getElements("xiaJiaNum").size();
			Log.logInfo("已下架商品的数量=" + canNotBuyNum);
			Log.logInfo("购物车已下架商品展示 测试场景通过");
			Assert.assertTrue(true);

		} else {
			Log.logInfo("购物车已下架商品展示 测试场景不通过");
			Assert.fail("购物车没有已下架商品展示，请重新检查");
		}

	}

	// 购物车页面验证商品“库存不足”
	@Test(dataProvider = "providerMethod")
	public void noStockCheck(Map<String, String> param) throws IOException,
			InterruptedException {
		// 打开H5首页
		openURL();
		// 进入购物车页面
		ShoppingCartPage sp = new ShoppingCartPage(driver);
		sp.getElement("shoppingcartInFirstPage").click();
		// 判断未登录的购物车的样式展示
		String notLoginText = sp.getElement("loginNow").getText();
		Log.logInfo("未登录= " + notLoginText);
		sp.getElement("loginNow").click();
		sp.getElement("username").sendKeys(param.get("username"));
		sp.getElement("password").sendKeys(param.get("password"));
		sp.getElement("submitButton").click();
		String noStockNum = "//div[contains(text(),'无 货')]";

		// 查找无货商品，购物车中没有找到无货字样的，则判断用例失败
		if (isElementExists(noStockNum)) {
			int canNotBuyNum = sp.getElements("xiaJiaNum").size();
			Log.logInfo("无货商品的数量=" + canNotBuyNum);
			Log.logInfo("购物车页面验证商品“库存不足” 测试场景通过");
			Assert.assertTrue(true);

		} else {
			Log.logInfo("购物车中没有无库存的商品，请重新检查");
			Log.logInfo("购物车页面验证商品“库存不足” 测试场景不通过");
			Assert.fail("购物车中没有无库存的商品，请重新检查");
		}

	}
	// 购物车中编辑单个商品-删除商品
			@Test(dataProvider = "providerMethod")
			public void oneGoodsEditDelete(Map<String, String> param) throws IOException,InterruptedException {
				// 打开H5首页
				openURL();
				// 进入购物车页面
				ShoppingCartPage sp = new ShoppingCartPage(driver);
				sp.getElement("shoppingcartInFirstPage").click();
				// 判断未登录的购物车的样式展示
				String notLoginText = sp.getElement("loginNow").getText();
				Log.logInfo("未登录= " + notLoginText);
				sp.getElement("loginNow").click();
				sp.getElement("username").sendKeys(param.get("username"));
				sp.getElement("password").sendKeys(param.get("password"));
				sp.getElement("submitButton").click();
				//WebElement submitBtn = sp.getElement("toOrderPaySubmit");
				int cartGoodsNum =  this.getCartNum(sp);
				Log.logInfo("当前购物车中有商品数量= " + cartGoodsNum);
				if (cartGoodsNum <= 0) {
					Log.logError("购物车中没有可编辑的商品");
					Assert.fail("购物车中没有可编辑的商品");
					return;
				} else {
					// 点击购物车中某个商品的编辑按钮
					String canNotBuy = "//div[@class='cart-show-list']/div/label/img[@name='canNotBuyImg']";
					if (!isElementExists(canNotBuy)) {
						//获取实际的商品数量
						List<WebElement> actualGoodsNum =this.getActualCartNum(sp);
						//获取checkbox
						List<WebElement> checkbox = this.getAllCheckBox(sp);
						for (int i = 0; i < actualGoodsNum.size(); i++) {
							if (checkbox.get(i).getAttribute("canbuy").equals("yes")) {
								Log.logInfo("当前找到的商品正常的商品，可以编辑");
								sp.getElement("GoodsEdit").click();
								actualGoodsNum.get(i).findElement(By.xpath("//div/div[2]/div[4]/div/div[1]/div[1]")).click();
								actualGoodsNum.get(i).findElement(By.xpath("//div/div[2]/div[4]/div/div[1]/div[2]")).click();
								wait(3000);
								int cartGoodNumNow =  this.getCartNum(sp);
								Log.logInfo("当前购物车中有商品数量= " + cartGoodNumNow);
								if ((cartGoodsNum-cartGoodNumNow) == 1) {
									Log.logInfo("购物车中编辑单个商品-删除  测试场景通过");
									Assert.assertTrue(true);
								} else {
									Log.logInfo("购物车中编辑单个商品-删除  测试场景不通过");
									Assert.fail();
								}
								break;
							} else {
								continue;
							}
						}
					}

				}

			}
	// 购物车中编辑单个商品-增加商品数量
	@Test(dataProvider = "providerMethod")
	public void oneGoodsEditAddNum(Map<String, String> param) throws IOException,
			InterruptedException {
		// 打开H5首页
		openURL();
		// 进入购物车页面
		ShoppingCartPage sp = new ShoppingCartPage(driver);
		sp.getElement("shoppingcartInFirstPage").click();
		// 判断未登录的购物车的样式展示
		String notLoginText = sp.getElement("loginNow").getText();
		Log.logInfo("未登录= " + notLoginText);
		sp.getElement("loginNow").click();
		sp.getElement("username").sendKeys(param.get("username"));
		sp.getElement("password").sendKeys(param.get("password"));
		sp.getElement("submitButton").click();
		//WebElement submitBtn = sp.getElement("toOrderPaySubmit");
		int cartGoodsNum =  this.getCartNum(sp);
		Log.logInfo("当前购物车中有商品数量= " + cartGoodsNum);
		if (cartGoodsNum <= 0) {
			Log.logError("购物车中没有可编辑的商品");
			Assert.fail("购物车中没有可编辑的商品");
			return;
		} else {
			// 点击购物车中某个商品的编辑按钮
			String canNotBuy = "//div[@class='cart-show-list']/div/label/img[@name='canNotBuyImg']";
			if (!isElementExists(canNotBuy)) {
				List<WebElement> actualGoodsNum = this.getActualCartNum(sp);
				List<WebElement> checkbox =this.getAllCheckBox(sp);
				for (int i = 0; i < actualGoodsNum.size(); i++) {
					if (checkbox.get(i).getAttribute("canbuy").equals("yes")) {
						Log.logInfo("当前找到的商品正常的商品，可以编辑");
						sp.getElement("GoodsEdit").click();
						actualGoodsNum.get(i).findElement(By.xpath("//div/div[2]/div[4]/div/div[2]/div[1]/div[3]/img")).click();
						actualGoodsNum.get(i).findElement(By.xpath("//div/div[2]/div[4]/div/div[1]/div[2]")).click();
						wait(3000);
						int cartGoodNumNow =  this.getCartNum(sp);
						Log.logInfo("当前购物车中有商品数量= " + cartGoodNumNow);
						if ((cartGoodNumNow - cartGoodsNum) == 1) {

							Log.logInfo("购物车中编辑单个商品-增加商品数量  测试场景通过");
							Assert.assertTrue(true);
						} else {
							Log.logInfo("购物车中编辑单个商品-增加商品数量  测试场景不通过");
							Log.logInfo("当前购物车中有商品数量= " + cartGoodNumNow);
							Assert.fail();

						}
						break;
					} else {
						continue;
					}
				}
			}

		}

	}
	// 购物车中编辑单个商品-减少商品数量
		@Test(dataProvider = "providerMethod")
		public void oneGoodsEditDeleteNum(Map<String, String> param) throws IOException,
				InterruptedException {
			// 打开H5首页
			openURL();
			// 进入购物车页面
			ShoppingCartPage sp = new ShoppingCartPage(driver);
			sp.getElement("shoppingcartInFirstPage").click();
			// 判断未登录的购物车的样式展示
			String notLoginText = sp.getElement("loginNow").getText();
			Log.logInfo("未登录= " + notLoginText);
			sp.getElement("loginNow").click();
			sp.getElement("username").sendKeys(param.get("username"));
			sp.getElement("password").sendKeys(param.get("password"));
			sp.getElement("submitButton").click();
			//WebElement submitBtn = sp.getElement("toOrderPaySubmit");
			int cartGoodsNum =  this.getCartNum(sp);
			Log.logInfo("当前购物车中有商品数量= " + cartGoodsNum);
			if (cartGoodsNum <= 0) {
				Log.logError("购物车中没有可编辑的商品");
				Assert.fail("购物车中没有可编辑的商品");
				return;
			} else {
				// 点击购物车中某个商品的编辑按钮
				String canNotBuy = "//div[@class='cart-show-list']/div/label/img[@name='canNotBuyImg']";
				if (!isElementExists(canNotBuy)) {
					List<WebElement> actualGoodsNum = this.getActualCartNum(sp);
					List<WebElement> checkbox = this.getAllCheckBox(sp);
					for (int i = 0; i < actualGoodsNum.size(); i++) {
						if (checkbox.get(i).getAttribute("canbuy").equals("yes")) {
							Log.logInfo("当前找到的商品正常的商品，可以编辑");
							sp.getElement("GoodsEdit").click();
							actualGoodsNum.get(i).findElement(By.xpath("//div/div[2]/div[4]/div/div[2]/div[1]/div[1]/img")).click();
							actualGoodsNum.get(i).findElement(By.xpath("//div/div[2]/div[4]/div/div[1]/div[2]")).click();
							wait(3000);
							int cartGoodNumNow = this.getCartNum(sp);
							Log.logInfo("当前购物车中有商品数量= " + cartGoodNumNow);
							if ((cartGoodsNum-cartGoodNumNow) == 1) {
								Log.logInfo("购物车中编辑单个商品-减少商品数量  测试场景通过");
								Assert.assertTrue(true);
							} else {
								Log.logInfo("购物车中编辑单个商品-减少商品数量  测试场景不通过");
								Log.logInfo("当前购物车中有商品数量= " + cartGoodNumNow);
								Assert.fail();

							}
							break;
						} else {
							continue;
						}
					}
				}

			}

		}
	// 正则方法取购物车中可结算的商品数量
	public int getCartNum(ShoppingCartPage sp) {
		// 提取数字
		WebElement submitBtn = sp.getElement("toOrderPaySubmit");
		String cartNum=submitBtn.getText();
		return Integer.parseInt(Pattern.compile("[^0-9]").matcher(cartNum).replaceAll(""));
	}
	// 取购物车中真正的的商品数量，包括已下架，无货等
	public List<WebElement> getActualCartNum(ShoppingCartPage sp ){
		List<WebElement> actualGoodsNum = sp.getElements("actualGoodsNum");
	    return actualGoodsNum;
	}
	//获取所有的checkbox
	public List<WebElement> getAllCheckBox(ShoppingCartPage sp ){
		List<WebElement> actualGoodsNum = sp.getElements("goodCheckBox");
	    return actualGoodsNum;
	}
	// 购物车中增加商品库存校验功能
	@Test(dataProvider = "providerMethod")
	public void addGoodNumCheckStock(Map<String, String> param)
			throws IOException, InterruptedException {
		// 打开H5首页
		openURL();
		// 进入购物车页面
		ShoppingCartPage sp = new ShoppingCartPage(driver);
		sp.getElement("shoppingcartInFirstPage").click();
		// 判断未登录的购物车的样式展示
		String notLoginText = sp.getElement("loginNow").getText();
		Log.logInfo("未登录= " + notLoginText);
		sp.getElement("loginNow").click();
		sp.getElement("username").sendKeys(param.get("username"));
		sp.getElement("password").sendKeys(param.get("password"));
		sp.getElement("submitButton").click();
		WebElement submitBtn = sp.getElement("toOrderPaySubmit");
		int submitNum =  this.getCartNum(sp);
		Log.logInfo("当前购物车中有商品数量= " + submitNum);
		if (submitNum <= 0) {
			Log.logError("购物车中没有可下单的商品");
			Assert.fail();
			return;
		} else {
			// 点击购物车中某个商品的编辑按钮
			sp.getElement("GoodsEdit").click();
			sp.getElement("buyNumber").sendKeys("999");
			Log.logInfo("库存校验提示： " + sp.getElement("StockCheckAlert").getText());
			int submitNum2 = Integer.parseInt(submitBtn.getText().substring(4,
					5));
			if (submitNum2 != 999) {
				Log.logInfo("购物车中增加商品库存校验功能  测试场景通过");
				Assert.assertTrue(true);
			} else {
				Log.logInfo("购物车中增加商品库存校验功能  测试场景不通过");
				Assert.fail();
			}
		}

	}

	// 未选择商品点击去结算
	@Test(dataProvider = "providerMethod")
	public void noGoodsToSubmit(Map<String, String> param) throws IOException,
			InterruptedException {
		// 打开H5首页
		openURL();
		// 进入购物车页面
		ShoppingCartPage sp = new ShoppingCartPage(driver);
		sp.getElement("shoppingcartInFirstPage").click();
		// 判断未登录的购物车的样式展示
		String notLoginText = sp.getElement("loginNow").getText();
		Log.logInfo("未登录= " + notLoginText);
		sp.getElement("loginNow").click();
		sp.getElement("username").sendKeys(param.get("username"));
		sp.getElement("password").sendKeys(param.get("password"));
		sp.getElement("submitButton").click();
		WebElement submitBtn = sp.getElement("toOrderPaySubmit");
		int submitNum = Integer.parseInt(submitBtn.getText().substring(4, 5));
		Log.logInfo("当前购物车中有商品数量= " + submitNum);
		if (submitNum <= 0) {
			Log.logError("购物车中没有可下单的商品");
			Assert.fail();
			return;
		} else {
			// 取消全选按钮
			sp.getElement("allChosen").click();
			int submitNum2 = Integer.parseInt(submitBtn.getText().substring(4,
					5));
			if (submitNum2 == 0) {
				submitBtn.click();
				Log.logInfo("点击了去结算后弹出提示： "
						+ sp.getElement("noGoodsSubmitAlert").getText());
				if (sp.getElement("noGoodsSubmitAlert").getText()
						.contains("请至少选择一件商品")) {
					Log.logInfo("“未选择商品点击去结算” 测试场景通过");
					Assert.assertTrue(true);
				} else {
					Log.logInfo("“未选择商品点击去结算” 测试场景不通过");
					Assert.fail();
					return;
				}
			} else {
				Log.logInfo("“未选择商品点击去结算” 测试场景不通过");
				Assert.fail();
				return;
			}
		}

	}

	// 选择商品进入结算页面
	@Test(dataProvider = "providerMethod")
	public void oneGoodToSubmit(Map<String, String> param) throws IOException,
			InterruptedException {
		// 打开H5首页
		openURL();
		// 进入购物车页面
		ShoppingCartPage sp = new ShoppingCartPage(driver);
		sp.getElement("shoppingcartInFirstPage").click();
		// 判断未登录的购物车的样式展示
		String notLoginText = sp.getElement("loginNow").getText();
		Log.logInfo("未登录= " + notLoginText);
		sp.getElement("loginNow").click();
		sp.getElement("username").sendKeys(param.get("username"));
		sp.getElement("password").sendKeys(param.get("password"));
		sp.getElement("submitButton").click();
		WebElement submitBtn = sp.getElement("toOrderPaySubmit");
		int submitNum = Integer.parseInt(submitBtn.getText().substring(4, 5));
		Log.logInfo("当前购物车中有商品数量= " + submitNum);
		if (submitNum <= 0) {
			Log.logError("购物车中没有可下单的商品");
			Assert.fail();
		} else {
			// 预期的跳转链接地址
			String expectURL = driver.findElement(By.xpath("html/body/form"))
					.getAttribute("action");
			Log.logInfo("预期跳转的链接= " + expectURL);
			submitBtn.click();
			// 以出现实付款、立即支付按钮出现，当前页面当然URL为标准，判断订单确认页面加载成功
			// 实际的跳转链接地址
			this.wait(3000);
			String actualURL = this.getCurrentURL();
			Log.logInfo("实际的链接跳转" + driver.getCurrentUrl());
			Boolean flag = isElementEqual(expectURL, actualURL);
			String actualOrderPay = ".//*[@id='paymentOrderPanel']/div[2]/div[1]/div[1]";// 实付款
			String toPayButton = sp.getElement("toPayOrder").getText();// 支付按钮

			if (isElementExists(actualOrderPay) && toPayButton.contains("立即支付")
					&& flag) {

				Log.logInfo(sp.getElement("actualOrderPay").getText() + " 显示");
				Log.logInfo(sp.getElement("toPayOrder").getText() + " 按钮显示");

				Log.logInfo("进入订单确认页成功\n 选择商品进入结算页面 测试场景通过");
				Assert.assertTrue(true);
			} else {
				Log.logInfo("进入订单确认页失败\n 选择商品进入结算页面 测试场景不通过");
				Assert.fail();
			}
		}

	}

	// 购物车无商品样式展示
	@Test(dataProvider = "providerMethod")
	public void shoppinCartNoGoods(Map<String, String> param)
			throws IOException {
		// 打开H5首页
		openURL();
		// 进入购物车页面
		ShoppingCartPage sp = new ShoppingCartPage(driver);
		sp.getElement("shoppingcartInFirstPage").click();
		// 判断未登录的购物车的样式展示
		String notLoginText = sp.getElement("loginNow").getText();
		Log.logInfo("未登录= " + notLoginText);
		// 登录购物车
		sp.getElement("loginNow").click();
		sp.getElement("username").sendKeys(param.get("username"));
		sp.getElement("password").sendKeys(param.get("password"));
		sp.getElement("submitButton").click();
		String noGoodsText = sp.getElement("noGoodsInShoppingCart").getText();
		if (noGoodsText.contains("购物车已经饿瘪了")) {
			Log.logInfo(noGoodsText);
			Log.logInfo("购物车无商品样式展示  测试场景通过");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("购物车无商品样式展示  测试场景不通过");
			Assert.fail();
		}
	}

	// 购物车未登录样式展示
	@Test
	public void shoppingCartNoLogin() throws IOException {
		// 打开H5首页
		openURL();
		// 进入购物车页面
		ShoppingCartPage sp = new ShoppingCartPage(driver);
		sp.getElement("shoppingcartInFirstPage").click();
		// 判断未登录的购物车的样式展示
		String notLoginText = sp.getElement("loginRightNow").getText();
		String notLoginURL = sp.getElement("loginNow").getAttribute("href");
		Log.logInfo("未登录= " + notLoginText);
		Log.logInfo("未登录= " + notLoginURL);
		Log.logInfo(driver.findElement(By.xpath("html/body/div[2]/div[2]"))
				.getText());
		Log.logInfo(driver.findElement(By.xpath("html/body/div[2]/div[3]/a"))
				.getText());
		if (notLoginText.contains("立即登录")) {
			Log.logInfo("购物车未登录样式展示  测试场景通过");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("购物车未登录样式展示  测试场景不通过");
			Assert.fail();
		}

	}

}
