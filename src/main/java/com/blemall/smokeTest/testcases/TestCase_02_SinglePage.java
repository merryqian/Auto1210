package com.blemall.smokeTest.testcases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.blemall.smokeTest.page.SinglePage;
import com.test.base.TestBase;
import com.test.bean.Config;
import com.test.util.Assertion;
import com.test.util.ExcelReader;
import com.test.util.Log;
import com.test.util.ScreenShot;
import com.test.util.TimeString;

public class TestCase_02_SinglePage extends TestBase {
   String excelPath = "D:\\code\\H5\\TestCase\\H5_SingleProduct.xlsx";
   String sheetName = "SingleProduct";
   ExcelReader reader = new ExcelReader(excelPath, sheetName);
   TimeString ts = new TimeString();
   String timeString = ts.getDate()  ;
   ScreenShot screen = new ScreenShot(driver);
   Map<String, String> testcaseStatus = new HashMap<String, String>();

// 商品名称检查
@Test(dataProvider = "providerMethod")
public void goodName(Map<String, String> param) throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.goodNameCheck(sp,param);
}


// 商品价格检查
@Test(dataProvider = "providerMethod")
public void goodPrice(Map<String, String> param) throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.goodPriceCheck(sp,param);

}

// 单品页商品无评价
@Test(dataProvider = "providerMethod")
public void goodNoEvaluate(Map<String, String> param) throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.goodNoEvaluateCheck(sp,param);

}

// 单品页商品有评价记录
@Test(dataProvider = "providerMethod")
public void goodEvaluate(Map<String, String> param) throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.goodEvaluateCheck(sp,param);

}


// 促销活动展示
@Test(dataProvider = "providerMethod")
public void goodPromotion(Map<String, String> param) throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.goodPromotionCheck(sp,param);

}

// 普通商品单品页是否存在“加入购物车”按钮
@Test(dataProvider = "providerMethod")
public void addToCartButton(Map<String, String> param) throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.addToCartButtonCheck(sp,param);
}

// 查看售后服务选择检查
@Test(dataProvider = "providerMethod")
public void customerService(Map<String, String> param) throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.customerServiceCheck(sp,param);
	
}


// 单品页供货方显示检查-自营
@Test(dataProvider = "providerMethod")
public void supplierCheckBySelf(Map<String, String> param)throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.supplierCheckBySelfCheck(sp,param);
	
	
}

// 单品页供货方显示检查-联营
@Test(dataProvider = "providerMethod")
public void supplierCheckByUnion(Map<String, String> param)throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.supplierCheckByUnionCheck(sp,param);
	
}


// 单品页支持自提显示检查-支持
@Test(dataProvider = "providerMethod")
public void takeBySelf(Map<String, String> param) throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.takeBySelfCheck(sp,param);
}

// 单品页退换货显示检查-不支持
@Test(dataProvider = "providerMethod")
public void returnOrChangeGoodsNotSupported(Map<String, String> param)throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.returnOrChangeGoodsNotSupportedCheck(sp,param);
}

// 单品页退换货显示检查-支持
@Test(dataProvider = "providerMethod")
public void returnOrChangeGoods(Map<String, String> param)throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.returnOrChangeGoodsCheck(sp,param);
	
}

// 单品页货到付款显示检查-支持
@Test(dataProvider = "providerMethod")
public void CODSupport(Map<String, String> param)throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.CODSupportCheck(sp,param);
	
}

// 单品页商品规格检查
@Test(dataProvider = "providerMethod")
public void productSpecification(Map<String, String> param) throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.productSpecificationCheck(sp,param);
	

	}


//单品页商品加入购物车检查
 @Test(dataProvider = "providerMethod")
public void addToShoppingCart(Map<String, String> param) throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.addToShoppingCartCheck(sp,param);
		
	}

//改变商品数量按钮检查
@Test(dataProvider = "providerMethod")
public void changeGoodNum(Map<String, String> param) throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.changeGoodNumCheck(sp,param);
	}

//单品页多件商品加入购物车检查
@Test(dataProvider = "providerMethod")
public void mutiGoodsToShoppingCart(Map<String, String> param) throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.mutiGoodsToShoppingCartCheck(sp,param);
	
	}


//数量大于实际库存加入购物车检查
@Test(dataProvider = "providerMethod")
public void moreThanKuCun(Map<String, String> param) throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.moreThanKuCunCheck(sp,param);
	
	}


//库存不足检查
@Test(dataProvider = "providerMethod")
public void outOfStock(Map<String, String> param) throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.outOfStockCheck(sp,param);
	
	
	}

//单品页商品详情检查
 @Test(dataProvider = "providerMethod")
public void goodDetails(Map<String, String> param) throws IOException {
		SinglePage sp = new SinglePage(driver);
		// 打开H5单品页
		this.openURL(param);
		// 判断单品页打开是否正常
		this.singlePageLoad(sp);
		//检查商品名称
		this.goodDetailsCheck(sp,param);
	}

//单品页商品推荐检查
@Test(dataProvider = "providerMethod")
public void goodRecommend(Map<String, String> param) throws IOException {
	SinglePage sp = new SinglePage(driver);
	// 打开H5单品页
	this.openURL(param);
	// 判断单品页打开是否正常
	this.singlePageLoad(sp);
	//检查商品名称
	this.goodRecommendCheck(sp,param);
	
	}


//打开H5首页
public void openURL(Map<String,String> param) {
	try {
		this.goTo(param.get("url") + param.get("productID"));
		Log.logInfo("打开连接=" + param.get("url") + param.get("productID"));
		} catch (Exception e) {
		Log.logInfo("打开失败");
		Assert.fail("页面打开失败");
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
	//H5单品页加载判断
	private void singlePageLoad(SinglePage sp) {
		try {
			if (sp.getElementNoWait("H5Load").isDisplayed()) {
				Log.logInfo("H5单品页加载成功");
			}

		} catch (Exception e) {
			Log.logError("页面加载失败：" + sp.getElement("H5Load").getText());
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
					"singlePageLoad","单品页加载");
			Assert.fail("页面加载失败");
		}	
	}
	//商品名称检查
	private void goodNameCheck(SinglePage sp,Map<String,String> param) {
		//获取预期的商品名称
		String goodsNameExpect=param.get("goodsName");
		//进入到商品名称
		WebElement scrollView = sp.getElementNoWait("GoodName");
		this.getIntoView(scrollView);
		//获取商品名称
		String goodNameActual = sp.getElementNoWait("GoodName").getText().substring(3);
		Log.logInfo("预期获取的商品名称= " + goodsNameExpect);
		Log.logInfo("实际获取的商品名称= " + goodNameActual);
		if(goodNameActual.equals(goodsNameExpect))
		{
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
					"goodName", "普通商品单品页商品名称检查");
		Log.logInfo("\"普通商品单品页商品名称检查\"测试场景通过");
		Assert.assertTrue(true);
		}
		else{
			Log.logInfo("\"普通商品单品页商品名称检查\"测试场景不通过");
			Assert.fail("商品名称获取与预期的不相等");
		}
			
	}
	//检查商品价格
	private void goodPriceCheck(SinglePage sp, Map<String, String> param) {
	    //获取预期的商品价格
		String goodsPriceExpect=param.get("goodsPrice");
		WebElement scrollView = sp.getElementNoWait("GoodName");
		this.getIntoView(scrollView);
		String goodsPriceActual= sp.getElementNoWait("GoodPrice").getText().substring(1);
		Log.logInfo("预期的商品价格= " + goodsPriceExpect);
		Log.logInfo("实际的商品价格= " + goodsPriceActual);
		if(goodsPriceActual.equals(goodsPriceExpect)){
			screen.snapshot((TakesScreenshot) driver, timeString, "Single","goodPrice","单品页商品价格检查");
			Log.logInfo("\"普通商品单品页价格显示\"测试场景通过");
			Assert.assertTrue(true);
		}
		else{
			screen.snapshot((TakesScreenshot) driver, timeString, "Single","goodPrice","单品页商品价格检查");
			Log.logInfo("\"普通商品单品页价格显示\"测试场景不通过");
			Assert.fail("单品页价格显示与预期不相等");
		}
		
	}
	//检查商品无评价
	private void goodNoEvaluateCheck(SinglePage sp, Map<String, String> param) {
	   //获取预期的无评价显示
		String expectText=param.get("expectText");
		WebElement scrollView = sp.getElementNoWait("GoodPrice");
		this.getIntoView(scrollView);
		String noEvaluteActual = sp.getElementNoWait("GoodNoEvalute").getText();
		Log.logInfo("无评价的商品预期显示文案= " + expectText);
		Log.logInfo("无评价的商品实际显示文案= " + noEvaluteActual);
		String expected = reader.getCellData(6, 6);
		Log.logInfo("预期显示文案= " + expected);
		if(noEvaluteActual.equals(expectText)){
			screen.snapshot((TakesScreenshot) driver, timeString, "Single","goodNoEvaluate","普通商品单品页无评价显示");
			Log.logInfo("单品页商品无评价 测试用例通过");
			Assert.assertTrue(true);
		}
		else{
			Log.logInfo("单品页商品无评价 测试用例不通过");
			Assert.fail("单品页商品无评价显示异常，或许是数据问题");
		}

	}
	//检查商品评价
	private void goodEvaluateCheck(SinglePage sp, Map<String, String> param) {
		WebElement scrollView = sp.getElementNoWait("GoodPrice");
		this.getIntoView(scrollView);
		if(sp.getElement("GoodMark").isDisplayed())
		   {
			Log.logInfo("该商品存在评价记录：\n"+sp.getElement("GoodMark").getText());
			//Log.logInfo("商品几颗星："+sp.getElement("GoodStar").getText());
			Log.logInfo("商品评论数："+sp.getElement("GoodEvaCount").getText());
			screen.snapshot((TakesScreenshot) driver, timeString, "Single","goodEvaluate","普通商品单品页有评价记录显示");
			Log.logInfo("\"普通商品单品页有评价记录显示\"测试场景通过");
			Assert.assertTrue(true);
			}
			else
				{
				Log.logInfo("\"普通商品单品页有评价记录显示\"测试场景不通过");
				screen.snapshot((TakesScreenshot) driver, timeString, "Single","goodEvaluate","普通商品单品页有评价记录显示");
				Assert.fail("商品评价显示异常");
				}
	}
	//单品页促销信息
	private void goodPromotionCheck(SinglePage sp, Map<String, String> param) {
		
		Log.logInfo("单品页面加载成功");
		WebElement scrollView = sp.getElementNoWait("GoodName");
		this.getIntoView(scrollView);
		if (sp.getElement("GoodPromotion").isDisplayed()) {
			List<WebElement> promotionType = sp
					.getElements("GoodPromotionType");
			for (int i = 0; i < promotionType.size(); i++) {
				Log.logInfo("促销活动类型= " + promotionType.get(i).getText());
			}
			sp.getElement("PromotionClick").click();
			List<WebElement> promotionName = sp
					.getElements("PromotionName");
			for (int i = 0; i < promotionType.size(); i++) {
				Log.logInfo("促销活动名称= " + promotionName.get(i).getText());
			}
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
					"goodPromotion","单品页商品促销活动展示");
			Log.logInfo("普通商品单品页普通商品“促销信息”显示" + "测试场景通过");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("普通商品单品页普通商品“促销信息”显示：\"" + "测试场景失败");
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
					"goodPromotion","单品促销活动检查失败");
			Assert.fail("单品页未看到促销信息");
			
		}	
}
	//检查加入购物车
	private void addToCartButtonCheck(SinglePage sp, Map<String, String> param) {
		
			WebElement button = sp.getElement("AddShoppingCartButton");
			if (button.isDisplayed()) {
				Log.logInfo("\"普通商品单品页是否存在“加入购物车”按钮：\"" + "测试场景通过");
				screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"addToCartButton","单品页加入购物车按钮检查");
				Assert.assertTrue(true);
			} else {
				Log.logInfo("\"普通商品单品页是否存在“加入购物车”按钮：\"" + "测试场景失败");
				Assert.fail("单品页未看到加入购物车按钮");
				screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"addToCartButton","单品页加入购物车按钮检查");
				
			}
	}
	//检查商品售后服务
	private void customerServiceCheck(SinglePage sp, Map<String, String> param) {
		WebElement scrollView = sp.getElementNoWait("GoodName");
		this.getIntoView(scrollView);
		WebElement service = sp.getElement("CustomerService");
		if (service.isDisplayed()) {
			Log.logInfo(service.getText() + ": "
					+ sp.getElement("ServiceContent").getText());
			Log.logInfo("\"查看售后服务选择检查:\"" + "测试场景通过");
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
					"customerService", "查看售后服务选择检查");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("\"查看售后服务选择检查:\"" + "测试场景失败");
			Assert.fail("单品页未看到售后服务内容");
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
					"customerService", "查看售后服务选择检查");
		}
	}
	//检查供应商
	private void supplierCheckBySelfCheck(SinglePage sp, Map<String, String> param) {
		//获取预期的显示
		String expectText=param.get("expectText");
		WebElement scrollView = sp.getElementNoWait("GoodName");
		this.getIntoView(scrollView);
		WebElement suplier = sp.getElement("SupplierCheckBySelf");
		Log.logInfo("单品页商品预期供应商= " +expectText);
		Log.logInfo("单品页商品实际供应商= " + suplier.getText());
		
		if (suplier.isDisplayed()&& suplier.getText().equals(expectText)) {
			Log.logInfo("\"单品页供货方显示检查-自营:\"" + "测试场景通过");
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"supplierCheckBySelf","单品页供货方显示检查-自营");
			Assert.assertTrue(true);
			} else {
				Log.logInfo("\"查看售后服务选择检查:\"" + "测试场景失败");
				screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"supplierCheckBySelf","单品页供货方显示检查-自营");
				Assert.fail("单品页未看到供货方");
				
			}
		
	}
	//单品页供应商-联营
	private void supplierCheckByUnionCheck(SinglePage sp, Map<String, String> param) {
		//预期的显示
		String expectText=param.get("expectText");
			WebElement scrollView = sp.getElementNoWait("GoodName");
			this.getIntoView(scrollView);
			WebElement suplier = sp.getElement("SupplierCheckBySelf");
			Log.logInfo("单品页商品预期供应商= " + expectText);
			Log.logInfo("单品页商品实际供应商= " + suplier.getText());
			if (suplier.getText().equals(expectText)) {
				Log.logInfo("\"单品页供货方显示检查-联营:\"" + "测试场景通过");
				screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"supplierCheckByUnion", "单品页供货方显示检查-联营");
				Assert.assertTrue(true);
			} else {
				Log.logInfo("\"查看售后服务选择检查:\"" + "测试场景失败");
				screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"supplierCheckByUnion", "单品页供货方显示检查-联营");
				Assert.fail("单品页看到的供应商与预期的不一致");

			}
	}
	//检查自提
	private void takeBySelfCheck(SinglePage sp, Map<String, String> param) {
		String expectText = param.get("expectText");
		WebElement scrollView = sp.getElementNoWait("GoodName");
		this.getIntoView(scrollView);
		WebElement suplier = sp.getElement("TakeBySelf");
		Log.logInfo("是否支持自提预期= " + expectText);
		Log.logInfo("是否支持自提实际= " + suplier.getText());
		if (suplier.isDisplayed() && suplier.getText().equals(expectText)) {
			Log.logInfo("\"单品页支持自提显示检查-支持:\"" + "测试场景通过");
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
					"takeBySelf", "单品页支持自提显示检查-支持");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("\"单品页支持自提显示检查-支持:\"" + "测试场景失败");
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
					"takeBySelf", "单品页支持自提显示检查-支持");
			Assert.fail("单品页未展示支持自提标示");

		}
	}
	//检查是否支持7天无理由退换货
	private void returnOrChangeGoodsNotSupportedCheck(SinglePage sp,Map<String, String> param) {
		   //获取预期的值
			String expectText=param.get("expectText");
			WebElement scrollView = sp.getElementNoWait("GoodName");
			this.getIntoView(scrollView);
			WebElement suplier = sp.getElement("ReturnOrChangeGoodsNotSupported");
			Log.logInfo("预期-是否支持7天无理由退换货= " + expectText);
			Log.logInfo("实际-是否支持7天无理由退换货= " + suplier.getText());
			if (suplier.isDisplayed()&&suplier.getText().equals(expectText)) {
				
				Log.logInfo("\"单品页退换货显示检查-不支持:\"" + "测试场景通过");
				screen.snapshot((TakesScreenshot) driver, timeString, "Single",
							"returnOrChangeGoodsNotSupported", "单品页退换货显示检查-不支持");
				Assert.assertTrue(true);
			} else {
				Log.logInfo("\"单品页退换货显示检查-不支持:\"" + "测试场景失败");
				screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"returnOrChangeGoodsNotSupported", "单品页退换货显示检查-不支持");
				Assert.fail("单品页未展示不支持7天无理由退换货标示");
				
				}
		}

	//检查支持7天无理由退换货
	private void returnOrChangeGoodsCheck(SinglePage sp,
			Map<String, String> param) {
		// 获取预期的值
		String expectText = param.get("expectText");
		WebElement scrollView = sp.getElementNoWait("GoodName");
		this.getIntoView(scrollView);
		WebElement suplier = sp.getElement("ReturnOrChangeGoodsSupported");
		Log.logInfo("预期-是否支持7天无理由退换货= " + expectText);
		Log.logInfo("实际-是否支持7天无理由退换货= " + suplier.getText());
		if (suplier.isDisplayed() && suplier.getText().equals(expectText)) {
			Log.logInfo("\"单品页退换货显示检查-支持:\"" + "测试场景通过");
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
					"returnOrChangeGoods", "单品页退换货显示检查-支持");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("\"单品页退换货显示检查-支持:\"" + "测试场景失败");
			Assert.fail("单品页未展示支持7天无理由退换货标示");
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
					"returnOrChangeGoods", "单品页退换货显示检查-支持");
		}

	}
	
	//检查货到付款
	private void CODSupportCheck(SinglePage sp, Map<String, String> param) {
		//获取预期的显示
		String expectText=param.get("expectText");
		WebElement scrollView = sp.getElementNoWait("GoodName");
		this.getIntoView(scrollView);
		WebElement suplier = sp.getElement("CODSupport");
		Log.logInfo("预期-是否支持货到付款= " + expectText);
		Log.logInfo("实际-是否支持货到付款= " + suplier.getText());
		if (suplier.isDisplayed()&&suplier.getText().equals(expectText)) {
			Log.logInfo("\"单品页货到付款显示检查-支持:\"" + "测试场景通过");
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"CODSupport","单品页货到付款显示检查-支持");
			Assert.assertTrue(true);
			} else {
			Log.logInfo("\"单品页货到付款显示检查-支持:\"" + "测试场景失败");
			Assert.fail("单品页未展示支持货到付款");
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"CODSupport","单品页货到付款显示检查-支持");
			}
	}
	//选择规格
	private void productSpecificationCheck(SinglePage sp, Map<String, String> param) {
		WebElement scrollView = sp.getElementNoWait("GoodName");
		this.getIntoView(scrollView);
		WebElement specification1 = sp.getElement("Chooice");
		WebElement specification2 = sp.getElement("Specification");
		if (specification1.isDisplayed()) {
			Log.logInfo(specification1.getText()+specification2.getText());
			Assert.assertEquals(specification1.getText(), "选择");
			specification1.click();
			//规格选择红色
			sp.getElement("Red").click();
			//尺寸选择50
			sp.getElement("SKUAttributeBtn").click();
			Log.logInfo("\"单品页商品规格选择检查:\"" + "测试场景通过");
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"productSpecification","单品页商品规格选择检查-成功");
			Assert.assertTrue(true);
			} else {
				Log.logInfo("\"单品页商品规格选择检查:\"" + "测试场景失败");
				Assert.fail("单品页未展示选择规格选项");
				screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"productSpecification","单品页商品规格选择检查-失败");
			}
	}
	//加入购物车
	private void addToShoppingCartCheck(SinglePage sp, Map<String, String> param) {

		WebElement scrollView = sp.getElementNoWait("GoodName");
		Log.logInfo("单品页加载成功");
		this.getIntoView(scrollView);
		WebElement addbutton = sp.getElement("AddShoppingCartButton");
		if (addbutton.isDisplayed()) {
			Log.logInfo(addbutton.getText());
			String goodNumBefore=sp.getElement("GoodNum").getText();
			Log.logInfo("当前购物车中有商品数量= "+goodNumBefore);
			addbutton.click();
			//规格选择红色
			sp.getElement("Red").click();
			//尺寸选择50
			sp.getElement("SKUAttributeBtn").click();
			//点击确定
			sp.getElement("AddButton").click();
			//捕捉加入购物车成功弹出框提示信息
			String success=sp.getElement("AddSuccess").getText();
			if(success.equals("加入购物车成功"))
				{
				Log.logInfo("成功提示信息： "+success);
				Assert.assertEquals(success,"加入购物车成功");
				String goodNumAfter=sp.getElement("NumAfter").getText();
				Log.logInfo("加入之后购物车中有商品数量= "+goodNumAfter);
				//因未登录，所以每次购物车图标应只有一件商品
				if(Integer.parseInt(goodNumAfter)== 1);{
				Log.logInfo("\"单品页商品加入购物车检查:\"" + "测试场景通过");
					screen.snapshot((TakesScreenshot) driver, timeString, "Single",
							"addToShoppingCart","单品页商品加入购物车检查-成功");
					Assert.assertTrue(true);}
				}else{
					Log.logInfo("失败提示信息： "+success);
					Assert.fail("单品页商品加入购物车失败");
					screen.snapshot((TakesScreenshot) driver, timeString, "Single",
							"addToShoppingCart","单品页商品加入购物车检查-失败");
				}
				
			} else {
				Log.logInfo("\"单品页商品加入购物车检查:\"" + "测试场景失败");
				Assert.fail("单品页商品加入购物车失败");
				screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"addToShoppingCart","单品页商品加入购物车检查-失败");
			}

	}
	
	
//改变商品数量
private void changeGoodNumCheck(SinglePage sp, Map<String, String> param) {

		WebElement scrollView = sp.getElementNoWait("GoodName");
		Log.logInfo("单品页加载成功");
		this.getIntoView(scrollView);
		WebElement inputNum = sp.getElement("InputNum");
		int num1=Integer.parseInt(inputNum.getAttribute("value"));
		Log.logInfo("商品数量="+inputNum.getAttribute("value"));
		if (inputNum.isDisplayed()) {
			
			if(inputNum.getAttribute("value").equals("1"))
			{
			  Log.logInfo("输入框中的商品数量为1,不可再点击“-”");
			  sp.getElement("IncreaseGoodNum").click();
			  WebElement inputNum2=sp.getElement("InputNum");
			  Log.logInfo("点击“+”号后输入框商品数量= "+inputNum2.getAttribute("value"));
			  int num2=Integer.parseInt(inputNum2.getAttribute("value"));
			  Log.logInfo("num1= "+num1);
			  Log.logInfo("num2= "+num2);
			  //点击一次增加按钮，数量增加1
			  if((num2-num1)==1);{
			  Log.logInfo("\"改变商品数量按钮检查\"测试场景通过");
			  screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"changeGoodNum","改变商品数量按钮检查");
			  Assert.assertTrue(true);
			}
			}
			else{
				Log.logInfo("输入框中的商品数量= "+sp.getElement("InputNum").getAttribute("value"));
				sp.getElement("DecreaseGoodNum").click();
				Log.logInfo("点击“-”号后输入框中的商品数量= "+sp.getElement("InputNum").getAttribute("value"));
			}
		  
		}else{
			Log.logInfo("输入框商品数量框显示异常，请查看");
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
					"changeGoodNum","改变商品数量按钮检查");
			Assert.fail("改变商品数量按钮异常");
		}
}
//多个商品加入购物车
private void mutiGoodsToShoppingCartCheck(SinglePage sp,Map<String, String> param) {
	WebElement scrollView = sp.getElementNoWait("GoodName");
	Log.logInfo("单品页加载成功");
	this.getIntoView(scrollView);
	WebElement inputNum = sp.getElement("InputNum");
	int num1=Integer.parseInt(inputNum.getAttribute("value"));
	Log.logInfo("商品数量="+num1);
	String expectedNum=param.get("goodNum");
	if (inputNum.isDisplayed()) {
		int forNum=Integer.parseInt(param.get("goodNum"));
		Log.logInfo("预期加入购物车的数量= "+forNum);
		inputNum.clear();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		inputNum.sendKeys(expectedNum);
		sp.getElementNoWait("AddShoppingCartButton").click();
		sp.getElementNoWait("AddButton").click();
		String success=sp.getElement("AddSuccess").getText();
		if(success.equals("加入购物车成功"))
			{
			Log.logInfo("成功提示信息： "+success);
			Assert.assertEquals(success,"加入购物车成功");
			String goodNumAfter=sp.getElement("NumAfter").getText();
			Log.logInfo("加入之后购物车中有商品数量= "+goodNumAfter);
			if(goodNumAfter==expectedNum );
			{
			Log.logInfo("\"单品页商品加入购物车检查:\"" + "测试场景通过");
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"mutiGoodsToShoppingCart","单品页商品加入购物车检查-成功");
			Assert.assertTrue(true);
			   }
			}else{
				Log.logInfo("失败提示信息： "+success);
				screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"mutiGoodsToShoppingCart","单品页商品加入购物车检查-失败");
				Assert.fail("单品页商品加入购物车失败");
			}
		  
		}else{
			Log.logInfo("输入框商品数量框显示异常，请查看");
			Assert.fail("输入框商品数量框显示异常，请查看");
		}
}

//数量大于实际库存加入购物车检查
private void moreThanKuCunCheck(SinglePage sp, Map<String, String> param) {
     String expectText=param.get("expectText");
	WebElement scrollView = sp.getElementNoWait("GoodName");
	Log.logInfo("单品页加载成功");
	this.getIntoView(scrollView);
	WebElement inputNum = sp.getElement("InputNum");
	int num1=Integer.parseInt(inputNum.getAttribute("value"));
	Log.logInfo("商品数量="+num1);
	String expectedNum=param.get("goodNum");
	if (inputNum.isDisplayed()) {
		int forNum=Integer.parseInt(param.get("goodNum"));
		Log.logInfo("预期加入购物车的数量= "+forNum);
		inputNum.clear();
		wait(3000);
		inputNum.sendKeys(expectedNum);
		sp.getElementNoWait("AddShoppingCartButton").click();
		sp.getElementNoWait("AddButton").click();
		String fail=sp.getElement("KuCunBuZu").getText();
		if(fail.equals(expectText))
		{
		  Log.logInfo("提示信息： "+fail);
			Assert.assertEquals(fail,"库存不足");
			Log.logInfo("\"数量大于实际库存加入购物车检查:\"" + "测试场景通过");
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"moreThanKuCun","数量大于实际库存加入购物车检查-成功");
			Assert.assertTrue(true);
		}else{
			Log.logInfo("失败提示信息： "+fail);
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"moreThanKuCun","单品页商品加入购物车超过库存检查-失败");
			Assert.fail("单品页商品加入购物车超过库存失败");
				
			}
		   
		}else{
			Log.logInfo("输入框商品数量框显示异常，请查看");
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
					"moreThanKuCun","输入框商品数量框显示异常，请查看");
			Assert.fail("输入框商品数量框显示异常，请查看");		
			}
  }

//单品页商品详情选择检查
private void goodDetailsCheck(SinglePage sp, Map<String, String> param) {
	//获取预期的详情
	String expectDetails=param.get("goodsDetailsImg");
		WebElement scrollView = sp.getElementNoWait("GoodName");
		Log.logInfo("单品页加载成功");
		this.getIntoView(scrollView);
		WebElement goodDetails = sp.getElement("GoodDetails");
	
		goodDetails.click();
		String actualDetails=sp.getElement("detailsImg").getAttribute("src");
		if(actualDetails.equals(expectDetails))
		{
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
					"goodDetails","单品页商品详情选择检查通过");
			Assert.assertTrue(true);   
		}else{
			Log.logInfo("单品页的商品详情按钮显示异常，请查看");
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
					"goodDetails","单品页商品详情选择检查失败");
			Assert.fail("单品页商品详情选择检查失败");
		}

}

//检查商品推荐功能
private void goodRecommendCheck(SinglePage sp, Map<String, String> param) {
 WebElement scrollView = sp.getElementNoWait("GoodRecommend");
	Log.logInfo("单品页加载成功");
	this.getIntoView(scrollView);
	WebElement goodRecommend = sp.getElement("GoodRecommend");
	if (goodRecommend.isDisplayed()) {
	  Log.logInfo(goodRecommend.getText()+" 加载成功");
	  List<WebElement> list=sp.getElements("RecommendGoodsList");
	  Log.logInfo("单品页推荐商品列表个数= "+list.size());
	  WebElement goods=null;
		for(int i=1;i<=list.size();i++)
		{
		goods=driver.findElement(By.xpath(".//*[@id='scroller']/div[9]/div/ul/li["+i+"]/div/div[2]/a"));
		Log.logInfo("热门推荐商品= "+goods.getText());
		}
		screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"goodRecommend","单品页商品详情选择检查-通过");
		Assert.assertTrue(true);
		}else{
			Log.logInfo("单品页的商品详情按钮显示异常，请查看");
			Assert.fail("单品页的商品详情按钮显示异常，请查看");
		}
	
}
//库存不足检查
private void outOfStockCheck(SinglePage sp, Map<String, String> param) {
	WebElement scrollView = sp.getElementNoWait("GoodName");
	this.getIntoView(scrollView);
	WebElement addButton = sp.getElement("AddShoppingCartButton");
	if (addButton.isDisplayed()) {
	//点击加入购物车按钮	
	sp.getElementNoWait("AddShoppingCartButton").click();
	Log.logInfo("库存不足标志： "+sp.getElement("OutOfStock").getText());
	if(sp.getElement("OutOfStock").getText().equals("已售罄"))
	{
	 //点击确定按钮
	 sp.getElementNoWait("AddButton").click();
	 String fail=sp.getElement("KuCunBuZu").getText();
		if(fail.equals("库存不足"))
		{
			Log.logInfo("提示信息： "+fail);
			Assert.assertEquals(fail,"库存不足");
			Log.logInfo("\"无库存商品加入购物车按钮检查:\"" + "测试场景通过");
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"outOfStock","无库存商品加入购物车按钮检查-成功");
			Assert.assertTrue(true);
			}else{
				Log.logInfo("失败提示信息： "+fail);
				screen.snapshot((TakesScreenshot) driver, timeString, "Single",
						"outOfStock","无库存商品加入购物车按钮检查-失败");
				Assert.fail("单品页检查无库存商品加入购物车失败");
				
			}
		}
		   
		}else{
			Log.logInfo("加入购物车的按钮显示异常，请查看");
			screen.snapshot((TakesScreenshot) driver, timeString, "Single",
					"outOfStock","无库存商品加入购物车按钮检查-失败");
			Assert.fail("加入购物车的按钮显示异常，请查看");
		}
		
}
}
