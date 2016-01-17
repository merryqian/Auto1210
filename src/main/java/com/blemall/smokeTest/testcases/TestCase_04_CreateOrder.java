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

import com.blemall.smokeTest.page.ToPayOrderPage;
import com.test.base.TestBase;
import com.test.bean.Config;
import com.test.util.Log;
import com.test.util.ScreenShot;
import com.test.util.TimeString;

public class TestCase_04_CreateOrder extends TestBase {
	TimeString ts = new TimeString();
	String timeString = ts.getDate();
	ScreenShot screen = new ScreenShot(driver);

	// 校验默认收货人的图标样式
	@Test(dataProvider = "providerMethod")
	public void defaultReceiverCheck(Map<String, String> param)throws IOException {
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法
		this.gotoShoppingCart(tp);
		// 调用检查默认收货地址方法
		this.checkDefaultAddress(tp);
	}

	// 检验无收获地址显示
	@Test(dataProvider = "providerMethod")
	public void noAddressCheck(Map<String, String> param) throws IOException {
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法
		this.gotoShoppingCart(tp);
		// 调用检查默认收货地址方法
		this.checkNoAddress(tp);
	}

	// 校验新增收货地址--选择地址列表页
	@Test(dataProvider = "providerMethod")
	public void addAddress(Map<String, String> param) throws IOException,
			InterruptedException {
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法
		this.gotoShoppingCart(tp);
		// 调用增加收货地址方法
		this.addressAddForList(tp, param);
		// 调用检查是否成功的方法
		this.isAddSuccess(tp, param);
	}

	// 校验新增收货地址--选择地址列表页
	@Test(dataProvider = "providerMethod")
	public void addAddressManager(Map<String, String> param)
			throws IOException, InterruptedException {
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法
		this.gotoShoppingCart(tp);
		// 调用增加收货地址方法
		this.addressAddForManager(tp, param);
		// 调用检查是否成功的方法
		this.isAddSuccess(tp, param);
	}

	// 校验新增收货地址收货人不填写错误提示语
	@Test(dataProvider = "providerMethod")
	public void addAddressNoReceiverName(Map<String, String> param)
			throws IOException, InterruptedException {
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法
		this.gotoShoppingCart(tp);
		// 调用增加收货地址方法
		this.addNoReceiverNameCheck(tp, param);

	}

	// 校验新增默认收货人信息联系电话错误提示语
	@Test(dataProvider = "providerMethod")
	public void addAddressWrongPhoneNum(Map<String, String> param)
			throws IOException, InterruptedException {
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法
		this.gotoShoppingCart(tp);
		// 调用增加收货地址方法
		this.wrongPhoneNum(tp, param);

	}

	// 编辑收货地址信息
	@Test(dataProvider = "providerMethod")
	public void addAddressEdit(Map<String, String> param) throws IOException,InterruptedException {
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法
		this.gotoShoppingCart(tp);
		// 调用编辑收货地址方法
		String receiverName = this.editAddress(tp, param, 1);
		//判断是否编辑成功
		this.isEditSuccess(tp, 1, receiverName, param);
	}

//	// 更换收货地址
//	@Test(dataProvider = "providerMethod")
//	public void addAddressSwitch(Map<String, String> param) throws IOException,InterruptedException {
//		ToPayOrderPage tp = new ToPayOrderPage(driver);
//		// 调用打开首页方法
//		this.openURL();
//		// 调用登录方法
//		this.login(tp, param.get("username"), param.get("password"));
//		// 调用打开购物车方法
//		this.gotoShoppingCart(tp);
//		// 调用编辑收货地址方法
//		String receiverName = this.editAddress(tp, param, 1);
//		this.isEditSuccess(tp, 1, receiverName, param);
//	}
	// 检查供应商展示
	@Test(dataProvider = "providerMethod")
	public void vendorCheck(Map<String, String> param) throws IOException,InterruptedException {
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法,并进行数据初始化
		this.initData(tp,param);
		// 调用进入结算页面方法
		this.gotoShoppingCartSP(tp);
		//判断供应商是否为预期
		this.checkVendor(tp, param);
	}
	// 检查业态展示
	@Test(dataProvider = "providerMethod")
	public void channelCheck(Map<String, String> param) throws IOException,InterruptedException {
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法,并进行数据初始化
		this.initData(tp,param);
		// 调用进入结算页面方法
		this.gotoShoppingCartSP(tp);
		//判断业态是否为预期
		this.checkChannel(tp, param);
	}
	// 校验单个包裹商品图片
	@Test(dataProvider = "providerMethod")
	public void goodsPictureCheck(Map<String, String> param) throws IOException,InterruptedException {
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法,并进行数据初始化
		this.initData(tp,param);
		// 调用进入结算页面方法
		this.gotoShoppingCartSP(tp);
		//判断结算页面的图片链接是否为预期
		this.checkPicture(tp, param);
	}
	// 校验单个包裹商品链接
	@Test(dataProvider = "providerMethod")
	public void goodsLinkCheck(Map<String, String> param) throws IOException,InterruptedException {
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法,并进行数据初始化
		this.initData(tp,param);
		// 调用进入结算页面方法
		this.gotoShoppingCartSP(tp);
		//判断结算页面的商品链接是否为预期
		this.checkGoodsLink(tp, param);
	}
	// 检查不支持七天无理由退换货展示
	@Test(dataProvider = "providerMethod")
	public void goodsReturnNotSupportedCheck(Map<String, String> param) throws IOException,InterruptedException {
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法,并进行数据初始化
		this.initData(tp,param);
		// 调用进入结算页面方法
		this.gotoShoppingCartSP(tp);
		//判断结算页面的商品是否支持7天无理由退换货
		this.checkGoodsReturnNotSupported(tp, param);
	}
	// 检查支持七天无理由退换货展示
	@Test(dataProvider = "providerMethod")
	public void goodsReturnSupportedCheck(Map<String, String> param) throws IOException,InterruptedException {
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法,并进行数据初始化
		this.initData(tp,param);
		// 调用进入结算页面方法
		this.gotoShoppingCartSP(tp);
		//判断结算页面的商品是否支持7天无理由退换货
		this.checkGoodsReturnSupported(tp, param);
	}
	// 检查默认配送方式及配送时段
	@Test(dataProvider = "providerMethod")
	public void sendTypeDefaultCheck(Map<String, String> param) throws IOException,InterruptedException {
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法,并进行数据初始化
		this.initData(tp,param);
		// 调用进入结算页面方法
		this.gotoShoppingCartSP(tp);
		//检查默认配送方式及配送时段
		this.checkDefaultSendType(tp, param);
	}
	// 选择配送时段
	@Test(dataProvider = "providerMethod")
	public void sendTimeChooseCheck(Map<String, String> param) throws IOException,InterruptedException {
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法,并进行数据初始化
		this.initData(tp,param);
		// 调用进入结算页面方法
		this.gotoShoppingCartSP(tp);
		//检查默认配送方式及配送时段
		this.checkSendTime(tp, param);
	}
	// 校验设置自提
	@Test(dataProvider = "providerMethod")
	public void selfTakeCheck(Map<String, String> param) throws IOException,InterruptedException {
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法,并进行数据初始化
		this.initData(tp,param);
		// 调用进入结算页面方法
		this.gotoShoppingCartSP(tp);
		//检查默认配送方式及配送时段
		this.checkSelfTake(tp, param);
	}
	//小于78元生鲜商品运费校验
	@Test(dataProvider="providerMethod")
	public void freshGoodsForLv1DeliveryFee(Map<String, String> param) throws IOException{
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法,并进行数据初始化
		this.initData(tp,param);
		// 调用进入结算页面方法
		this.gotoShoppingCartSP(tp);
		this.checkFreshGoodsForLv1DeliveryFee(tp,param);
	}
	//大于等于78元小于128元生鲜商品运费校验
	@Test(dataProvider="providerMethod")
	public void freshGoodsForLv2DeliveryFee(Map<String, String> param) throws IOException{
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法,并进行数据初始化
		this.initData(tp,param);
		// 调用进入结算页面方法
		this.gotoShoppingCartSP(tp);
		this.checkFreshGoodsForLv2DeliveryFee(tp,param);
	}
	//大于128元生鲜商品运费校验
	@Test(dataProvider="providerMethod")
	public void freshGoodsForLv3DeliveryFee(Map<String, String> param) throws IOException{
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法,并进行数据初始化
		this.initData(tp,param);
		// 调用进入结算页面方法
		this.gotoShoppingCartSP(tp);
		this.checkFreshGoodsForLv3DeliveryFee(tp,param);
	}
	//小于48元重量小于等于5kg常温商品
	@Test(dataProvider="providerMethod")
	public void normalGoodsScenario1(Map<String, String> param) throws IOException{
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法,并进行数据初始化
		this.initData(tp,param);
		// 调用进入结算页面方法
		this.gotoShoppingCartSP(tp);
		this.checknormalGoodsScenario1(tp,param);
	}
	//小于48元重量大于5kg常温商品
	@Test(dataProvider="providerMethod")
	public void normalGoodsScenario2(Map<String, String> param) throws IOException{
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法,并进行数据初始化
		this.initData(tp,param);
		// 调用进入结算页面方法
		this.gotoShoppingCartSP(tp);
		this.checknormalGoodsScenario2(tp,param);
	}
	//大于等于48元小于96元重量大于5kg常温商品
	@Test(dataProvider="providerMethod")
	public void normalGoodsScenario3(Map<String, String> param) throws IOException{
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法,并进行数据初始化
		this.initData(tp,param);
		// 调用进入结算页面方法
		this.gotoShoppingCartSP(tp);
		this.checknormalGoodsScenario3(tp,param);
	}
	//大于等于96元小于144元重量大于10kg常温商品
		@Test(dataProvider="providerMethod")
		public void normalGoodsScenario4(Map<String, String> param) throws IOException{
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法,并进行数据初始化
		this.initData(tp, param);
		// 调用进入结算页面方法
		this.gotoShoppingCartSP(tp);
		this.checknormalGoodsScenario4(tp, param);
	}
		// 在线支付检查
		@Test(dataProvider = "providerMethod")
		public void payNow(Map<String, String> param) throws IOException {
		ToPayOrderPage tp = new ToPayOrderPage(driver);
		// 调用打开首页方法
		this.openURL();
		// 调用登录方法
		this.login(tp, param.get("username"), param.get("password"));
		// 调用打开购物车方法,并进行数据初始化
		this.initData(tp, param);
		// 调用进入结算页面方法
		this.gotoShoppingCartSP(tp);
		this.checkPayNow(tp, param);
	}	
		// 货到付款
		@Test(dataProvider = "providerMethod")
		public void CODCheck(Map<String, String> param) throws IOException {
			ToPayOrderPage tp = new ToPayOrderPage(driver);
			// 调用打开首页方法
			this.openURL();
			// 调用登录方法
			this.login(tp, param.get("username"), param.get("password"));
			// 调用打开购物车方法,并进行数据初始化
			this.initData(tp, param);
			// 调用进入结算页面方法
			this.gotoShoppingCartSP(tp);
			
			this.checknormalGoodsCOD(tp, param);
		}
	private void checknormalGoodsCOD(ToPayOrderPage tp,Map<String, String> param) {
		String expected = param.get("payType");
		String payType = tp.getElement("payTypeContents").getText();
		Log.logInfo("默认支付方式： " + payType);
		// 点击支付方式
		tp.getElement("payTypeContents").click();
		// 选择货到付款
		wait(3000);
		String xpath = ".//*[@id='offLine']";
		if (isElementExists(xpath)) {
			Log.logInfo("支付方式=" + tp.getElementNoWait("COD").getText());
				tp.getElement("COD").click();
				// 点击 确定返回结算页面
				tp.getElement("payConfirm").click();
				if (tp.getElement("payTypeContents").getText().equals(expected)) {
					wait(3000);
					// 点击立即支付按钮
					tp.getElement("toPayButton").click();
					wait(3000);
					// 获取当前页面的标题
					String currentTitle = driver.getTitle();
					Log.logInfo("currentTitle=" + currentTitle);
					if (currentTitle.equals(param.get("currentTitle"))) {
						Log.logInfo("货到付款检查 测试用例场景通过");
						Assert.assertTrue(true);
					}
					 else {
							Log.logInfo("货到付款检查 测试用例场景不通过");
							Assert.fail("货到付款检查 测试用例场景不通过");
						}
				}
				 else {
						Log.logInfo("货到付款检查 测试用例场景不通过");
						Assert.fail("货到付款检查 测试用例场景不通过");
					}
			}

			else {
				Log.logInfo("货到付款检查 测试用例场景不通过");
				Assert.fail("货到付款检查 测试用例场景不通过");
			}

		}
	

	private void checkPayNow(ToPayOrderPage tp, Map<String, String> param) {
		String expected=param.get("payType");
		String payType=tp.getElement("payTypeContents").getText();
		if(payType.equals(expected))
		{
			Log.logInfo("默认支付方式： " + payType);
			//点击在线支付
			tp.getElement("payTypeContents").click();
			Log.logInfo(driver.findElement(By.id("onLine")).getText());
			//点击 确定返回结算页面
			tp.getElement("payConfirm").click();
			wait(3000);
			//点击立即支付按钮
			tp.getElement("toPayButton").click();
			 wait(3000);
			 //获取当前页面的URL
			 String currentURL=driver.getCurrentUrl();
			 Log.logInfo("currentURL=" +currentURL);
			 if(currentURL.equals(param.get("currentURL")))
			 {
			  Log.logInfo("在线支付检查 测试用例场景通过");
			  Assert.assertTrue(true);
			 }
			 else{
				 Log.logInfo("在线支付检查 测试用例场景不通过");
				Assert.fail("在线支付检查 测试用例场景不通过");
			 }
			

		} else {
			Log.logInfo("在线支付检查 测试用例场景不通过");
			Assert.fail("在线支付检查 测试用例场景不通过");
		}
	}


	private void checknormalGoodsScenario4(ToPayOrderPage tp,Map<String, String> param) {
		Double lowerAmount=Double.parseDouble(param.get("lowerAmount"));
		Double upperAmount=Double.parseDouble(param.get("upperAmount"));
		Double expectedFee= Double.parseDouble(param.get("deliveryFee"));
		
		String totalMoneyDesc = tp.getElement("totalOrderAmount").getText().substring(1);
		double totalMoney = Double.parseDouble(totalMoneyDesc);
		String deliveryFeeDesc = tp.getElement("totalDeliveryCost").getText().substring(1);
		double deliveryFee = Double.parseDouble(deliveryFeeDesc);
		Log.logInfo("商品总额= "+totalMoney);
		//Log.logInfo("商品总重量= "+totalWeight);
		Log.logInfo("运费= "+deliveryFee);
		if(totalMoney>=lowerAmount&&totalMoney<upperAmount){
			if(deliveryFee==expectedFee){
				Log.logInfo("大于等于48元小于96元重量大于5kg常温商品 测试场景通过");
				Assert.assertTrue(true);
			}else{
				Log.logInfo("大于等于48元小于96元重量大于5kg常温商品 测试场景不通过");
				Assert.fail("大于等于48元小于96元重量大于5kg常温商品 测试场景不通过");
			}
		}
		}

	private void checknormalGoodsScenario3(ToPayOrderPage tp,Map<String, String> param) {
		Double lowerAmount=Double.parseDouble(param.get("lowerAmount"));
		Double upperAmount=Double.parseDouble(param.get("upperAmount"));
		Double expectedFee= Double.parseDouble(param.get("deliveryFee"));
		
		String totalMoneyDesc = tp.getElement("totalOrderAmount").getText().substring(1);
		double totalMoney = Double.parseDouble(totalMoneyDesc);
		String deliveryFeeDesc = tp.getElement("totalDeliveryCost").getText().substring(1);
		double deliveryFee = Double.parseDouble(deliveryFeeDesc);
		Log.logInfo("商品总额= "+totalMoney);
		//Log.logInfo("商品总重量= "+totalWeight);
		Log.logInfo("运费= "+deliveryFee);
		if(totalMoney>=lowerAmount&&totalMoney<upperAmount){
			if(deliveryFee==expectedFee){
				Log.logInfo("大于等于48元小于96元重量小于5kg常温商品 测试场景通过");
				Assert.assertTrue(true);
			}else{
				Log.logInfo("大于等于48元小于96元重量小于5kg常温商品 测试场景不通过");
				Assert.fail("大于等于48元小于96元重量小于5kg常温商品 测试场景不通过");
			}
		}
	}

	private void checknormalGoodsScenario2(ToPayOrderPage tp,Map<String, String> param) {
		Double totalAmount=Double.parseDouble(param.get("amount"));
		Double expectedFee= Double.parseDouble(param.get("deliveryFee"));
		
		String totalMoneyDesc = tp.getElement("totalOrderAmount").getText().substring(1);
		double totalMoney = Double.parseDouble(totalMoneyDesc);
		String deliveryFeeDesc = tp.getElement("totalDeliveryCost").getText().substring(1);
		double deliveryFee = Double.parseDouble(deliveryFeeDesc);
		Log.logInfo("商品总额= "+totalMoney);
		//Log.logInfo("商品总重量= "+totalWeight);
		Log.logInfo("运费= "+deliveryFee);
		if(totalMoney<totalAmount){
			if(deliveryFee==expectedFee){
				Log.logInfo("小于48元重量大于5kg常温商品 测试场景通过");
				Assert.assertTrue(true);
			}else{
				Log.logInfo("小于48元重量大于5kg常温商品 测试场景不通过");
				Assert.fail("小于48元重量大于5kg常温商品 测试场景不通过");
			}
		}
	}

	private void checknormalGoodsScenario1(ToPayOrderPage tp,Map<String, String> param) {
		Double totalAmount=Double.parseDouble(param.get("amount"));
		//String totalWeight=param.get("weight");
		Double expectedFee= Double.parseDouble(param.get("deliveryFee"));
		
		String totalMoneyDesc = tp.getElement("totalOrderAmount").getText().substring(1);
		double totalMoney = Double.parseDouble(totalMoneyDesc);
		String deliveryFeeDesc = tp.getElement("totalDeliveryCost").getText().substring(1);
		double deliveryFee = Double.parseDouble(deliveryFeeDesc);
		Log.logInfo("商品总额= "+totalMoney);
		//Log.logInfo("商品总重量= "+totalWeight);
		Log.logInfo("运费= "+deliveryFee);
		if(totalMoney<totalAmount){
			if(deliveryFee==expectedFee){
				Log.logInfo("小于48元重量小于等于5kg常温商品 测试场景通过");
				Assert.assertTrue(true);
			}else{
				Log.logInfo("小于48元重量小于等于5kg常温商品 测试场景不通过");
				Assert.fail("小于48元重量小于等于5kg常温商品 测试场景不通过");
			}
		}
	}

	private void checkFreshGoodsForLv3DeliveryFee(ToPayOrderPage tp,Map<String, String> param) {
		Double totalAmount=Double.parseDouble(param.get("Level3Amount"));
		Double expectedFee= Double.parseDouble(param.get("deliveryFee"));
		
		String totalMoneyDesc = tp.getElement("totalOrderAmount").getText().substring(1);
		double totalMoney = Double.parseDouble(totalMoneyDesc);
		String deliveryFeeDesc = tp.getElement("totalDeliveryCost").getText().substring(1);
		double deliveryFee = Double.parseDouble(deliveryFeeDesc);
		Log.logInfo("商品总额= "+totalMoney);
		Log.logInfo("运费= "+deliveryFee);
		if(totalMoney>totalAmount){
			if(deliveryFee==expectedFee){
				Log.logInfo("大于128元生鲜商品运费校验测试场景通过");
				Assert.assertTrue(true);
			}else{
				Log.logInfo("大于128元生鲜商品运费校验 测试场景不通过");
				Assert.fail("大于128元生鲜商品运费校验 测试场景不通过");
			}
		}
	}
	

	private void checkFreshGoodsForLv2DeliveryFee(ToPayOrderPage tp, Map<String, String> param) {
		Double lowerAmount=Double.parseDouble(param.get("LowerAmount"));
		Double upperAmount=Double.parseDouble(param.get("upperAmount"));
		Double expectedFee= Double.parseDouble(param.get("deliveryFee"));
		
		String totalMoneyDesc = tp.getElement("totalOrderAmount").getText().substring(1);
		double totalMoney = Double.parseDouble(totalMoneyDesc);
		String deliveryFeeDesc = tp.getElement("totalDeliveryCost").getText().substring(1);
		double deliveryFee = Double.parseDouble(deliveryFeeDesc);
		Log.logInfo("商品总额= "+totalMoney);
		Log.logInfo("运费= "+deliveryFee);
		if(totalMoney>=lowerAmount&&totalMoney<upperAmount){
			if(deliveryFee==expectedFee){
				Log.logInfo("大于等于78元小于128元生鲜商品运费校验 测试场景通过");
				Assert.assertTrue(true);
			}else{
				Log.logInfo("大于等于78元小于128元生鲜商品运费校验 测试场景不通过");
				Assert.fail("大于等于78元小于128元生鲜商品运费校验 测试场景不通过");
			}
		}
	}

	private void checkFreshGoodsForLv1DeliveryFee(ToPayOrderPage tp, Map<String, String> param){
		Double totalAmount=Double.parseDouble(param.get("Level1Amount"));
		Double expectedFee= Double.parseDouble(param.get("deliveryFee"));
		String totalMoneyDesc = tp.getElement("totalOrderAmount").getText().substring(1);
		double totalMoney = Double.parseDouble(totalMoneyDesc);
		String deliveryFeeDesc = tp.getElement("totalDeliveryCost").getText().substring(1);
		double deliveryFee = Double.parseDouble(deliveryFeeDesc);
		Log.logInfo("商品总额= "+totalMoney);
		Log.logInfo("运费= "+deliveryFee);
		if(totalMoney<78){
			if(deliveryFee==expectedFee){
				Log.logInfo("小于78元生鲜商品运费校验 测试场景通过");
				Assert.assertTrue(true);
			}else{
				Log.logInfo("小于78元生鲜商品运费校验 测试场景不通过");
				Assert.fail("小于78元生鲜商品运费校验 测试场景不通过");
			}
		}
		
	}
	
	private void checkSelfTake(ToPayOrderPage tp, Map<String, String> param) {
		String expectSendType = param.get("sendType");
		String expectSendShop = param.get("sendShop");
		tp.getElement("sendTypeShow").click();
        if(tp.getElement("ziTi").getText().equals("门店自提"))	
        {
        	//点击门店自提
        	tp.getElement("ziTi").click();
        	Log.logInfo("点击门店自提");
        	wait(2000);
        	Log.logInfo("点击"+tp.getElement("ziTiShopChoose").getText());
        	tp.getElement("ziTiShopChoose").click();
        	wait(2000);
        	//选择自提区域
        	Log.logInfo(tp.getElement("takeSelfShopArea").getText());
        	tp.getElement("takeSelfShopArea").click();
        	wait(2000);
        	Log.logInfo(tp.getElement("takeSelfShop").getText());
        	tp.getElement("takeSelfShop").click();
        	tp.getElement("submit_selected_delivery_info").click();
        }
        else{
        	Log.logInfo("未找到门店自提选项，请检查该商品是否支持");
        	Assert.fail("未找到门店自提选项，请检查该商品是否支持");
        }
        //点击了确定后，返回到结算页面
		String actual = tp.getElement("sendTypeShow").getText();
		Log.logInfo("选择了自提门店后="+actual);
		if (actual.replaceAll("\\s*", "").equals(expectSendType+expectSendShop)) {
			Log.logInfo("选择门店自提  测试场景通过");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("校验设置自提  测试场景不通过");
			Assert.fail("检查选择门店自提后，自提门店与预期不一致");
		}
	}
	private void checkSendTime(ToPayOrderPage tp, Map<String, String> param) {
		String expectSendTime = param.get("SendTime");
		tp.getElement("sendTypeShow").click();
		//进入到选择配送时间段页面
		Log.logInfo("进入到选择配送时间段页面,列表如下");
		Log.logInfo(tp.getElement("sendTime").getText());
		Log.logInfo(tp.getElement("sendTimeList1").getText());
		Log.logInfo(tp.getElement("sendTimeList2").getText());
		Log.logInfo(tp.getElement("sendTimeList3").getText());
		tp.getElement("onlyHoliday").click();
		tp.getElement("submit_selected_delivery_info").click();
		String actual = tp.getElement("sendTimeShowAfterChoose").getText();
		Log.logInfo("选择了配送时段后="+actual);
		if (actual.replaceAll("\\s*", "").equals(expectSendTime)) {
			Log.logInfo("选择配送时段= " + actual);
			Log.logInfo("选择配送时段  测试场景通过");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("选择配送时段  测试场景不通过");
			Assert.fail("检查选择了配送时段后与预期不一致");
		}
	}

	private void checkDefaultSendType(ToPayOrderPage tp,Map<String, String> param) {
		String expectSendType = param.get("defaultSendType");
		String expectSendTime = param.get("defalutSendTime");
		String actual = tp.getElement("sendTypeShow").getText();
		Log.logInfo("配送信息="+actual);
		
		if (actual.replaceAll("\\s*", "").equals(expectSendType+expectSendTime)) {
			Log.logInfo("检查默认配送方式及配送时段= " + actual);
			Log.logInfo("检查默认配送方式及配送时段  测试场景通过");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("检查默认配送方式及配送时段  测试场景不通过");
			Assert.fail("检查默认配送方式及配送时段与预期不一致");
		}
	}

	private void checkGoodsReturnSupported(ToPayOrderPage tp,Map<String, String> param) {
		String expect = param.get("goodsReturn");
		String actual = tp.getElement("goodsReturnSupported").getText();
		if (actual.equals(expect)) {
			Log.logInfo("是否支持7天无理由退换货= " + actual);
			Log.logInfo("检查支持七天无理由退换货展示  测试场景通过");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("检查支持七天无理由退换货展示  测试场景不通过");
			Assert.fail("商品支持7天无理由退换货与预期不一致");
		}
	}

	private void checkGoodsReturnNotSupported(ToPayOrderPage tp,Map<String, String> param) {
		String expect = param.get("goodsReturn");
		String actual = tp.getElement("goodsReturnNotSupported").getText();
		if (actual.equals(expect)) {
			Log.logInfo("是否支持7天无理由退换货= " + actual);
			Log.logInfo("检查不支持七天无理由退换货展示  测试场景通过");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("检查不支持七天无理由退换货展示  测试场景不通过");
			Assert.fail("商品不支持7天无理由退换货与预期不一致");
		}
	}

	private void checkGoodsLink(ToPayOrderPage tp, Map<String, String> param) {
		String expect = param.get("link");
		String actual = tp.getElement("goodsLink").getAttribute("href");
		if (actual.equals(expect)) {
			Log.logInfo("商品链接名称= " + actual);
			Log.logInfo("检查商品链接  测试场景通过");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("校验单个包裹商品连接  测试场景不通过");
			Assert.fail("商品链接与预期不一致");
		}
	}

	private void checkPicture(ToPayOrderPage tp, Map<String, String> param) {
		String expect = param.get("picture");
		String actual = tp.getElement("pictureLink").getAttribute("src");
		if (actual.equals(expect)) {
			Log.logInfo("图片链接名称= " + actual);
			Log.logInfo("检查图片链接  测试场景通过");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("检查图片  测试场景不通过");
			Assert.fail("检查图片与预期不一致");
		}		
	}

	private void checkChannel(ToPayOrderPage tp, Map<String, String> param) {
		String expect = param.get("channelName");
		String actual = tp.getElement("channelName").getText();
		if (actual.equals(expect)) {
			Log.logInfo("业态渠道名称= " + actual);
			Log.logInfo("检查业态展示  测试场景通过");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("检查业态展示  测试场景不通过");
			Assert.fail("检查业态与预期不一致");
		}
	}

	private void initData(ToPayOrderPage tp,Map<String,String> param) {
		// 点击首页的购物车按钮
		tp.getElement("shoppingCart").click();
		Log.logInfo("点击首页的购物车按钮");
		String path="html/body/div[1]/div[2]";
		if(!isElementExists(path))
		{
			//清空购物车
			tp.getElement("editAll").click();
			tp.getElement("deleteBtn").click();
			tp.getElement("Completed").click();
			
		}
		driver.get("http://m.bl.com/h5-web/product/scanBuy.html?productid="+param.get("productID"));
		tp.getElement("addButton").click();
		tp.getElement("addButton").click();
		String success=tp.getElement("AddSuccess").getText();
		if(success.equals("加入购物车成功"))
		{
			Log.logInfo("成功提示信息： "+success);
			tp.getElement("toShoppingCart").click();
			
		}else{
			Log.logInfo("失败提示信息： "+success);
			Assert.fail("单品页商品加入购物车失败");
		}
		
	}

	private void checkVendor(ToPayOrderPage tp, Map<String, String> param) {
       String expect=param.get("vendorName");
       String actual=tp.getElement("vendorName").getText();
       if(actual.equals(expect))
       {
    	   Log.logInfo("供应商名称= "+actual);
    	   Log.logInfo("检查供应商展示  测试场景通过");
    	   Assert.assertTrue(true);
       }
       else{
    	   Log.logInfo("检查供应商展示  测试场景不通过");
    	   Assert.fail("检查供应商与预期不一致");
       }
		
		
	}

	private void isEditSuccess(ToPayOrderPage tp, int check,String editReveiver, Map<String, String> param) {
		wait(3000);
		String expect = param.get("reciverName");
		String actual = tp.getElement("receiverNameText").getText();
		Log.logInfo("编辑收货人修改后的收件人姓名= " + actual);
		if (actual.contains(expect)) {
			Log.logInfo("编辑收货地址成功 ");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("编辑收货地址信息 测试场景失败");
			Assert.fail("编辑收货地址信息 测试场景失败");
		}
	}

	private String editAddress(ToPayOrderPage tp, Map<String, String> param,int edit) {

		// 点击订单确认页的收货地址框
		tp.getElement("addressLink").click();
		Log.logInfo("点击订单确认页的收货地址框");
		wait(3000);
		// 管理按钮
		tp.getElement("addrManager").click();
		Log.logInfo("点击管理按钮");
		// 点击编辑收货地址按钮
		wait(3000);
		String editAddr = ".//*[@id='addrPanel']/section/div[" + edit+ "]/div[2]/div/ul/li[1]/a";
		driver.findElement(By.xpath(editAddr)).click();
		// 获取修改前的值
		String reciverName = ".//*[@id='addrPanel']/section/div[1]/div[" + edit+ "]/div[1]/ul/li[1]";
		WebElement reciverNameEl = driver.findElement(By.xpath(reciverName));
		String receiverName = reciverNameEl.getText();
		Log.logInfo("编辑收货人修改前的收件人姓名= " + reciverNameEl.getText());
		// 清空原有的收件人
		tp.getElement("reciverName").clear();
		tp.getElement("reciverName").sendKeys(param.get("reciverName"));
		// 点击保存
		tp.getElement("addressSubmit").click();
		return receiverName;
	}

	private void wrongPhoneNum(ToPayOrderPage tp, Map<String, String> param) {
		// 点击订单确认页的收货地址框
		tp.getElement("addressLink").click();
		Log.logInfo("点击订单确认页的收货地址框");
		wait(3000);
		// 点击地址新增按钮
		tp.getElement("addressAdd").click();
		Log.logInfo("点击地址新增按钮,弹出新增框");
		wait(3000);
		// 输入收件人
		tp.getElement("reciverName").sendKeys(param.get("reciverName"));
		Log.logInfo("输入收件人");
		// 输入错误的手机号码格式
		tp.getElement("reciverPhone").sendKeys(param.get("reciverPhone"));
		Log.logInfo("输入错误的手机号码格式");
		// 点击提交新增按钮
		tp.getElement("addressSubmit").click();
		Log.logInfo("点击保存按钮");
		String msgContext = tp.getElement("msgContext").getText();
		Log.logInfo("填写错误的手机号码格式，直接点击提交按钮提示： " + msgContext);
		if (msgContext.equals("手机号码格式有误")) {
			Log.logInfo("填写错误的手机号码格式，直接点击提交按钮提示： " + msgContext);
			Log.logInfo("校验新增默认收货人信息联系电话错误提示语  测试用例通过");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("校验新增默认收货人信息联系电话错误提示语 测试用例不通过");
			Assert.fail();
		}

	}

	public void login(ToPayOrderPage tp, String username, String password) {
		// 定位到首页的登录按钮位置
		WebElement scrollView = tp.getElement("location");
		this.getIntoView(scrollView);
		Log.logInfo("定位到首页的登录按钮位置");
		WebElement loginEntrance = tp.getElementNoWait("loginEntrance");
		loginEntrance.click();
		Log.logInfo("点击首页的登录按钮入口");
		tp.getElement("userName").sendKeys(username);
		Log.logInfo("用户名=" + username);
		tp.getElement("password").sendKeys(password);
		Log.logInfo("密码=" + password);
		tp.getElementNoWait("loginButton").click();
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

	public void gotoShoppingCart(ToPayOrderPage tp) {
		// 点击首页的购物车按钮
		tp.getElement("shoppingCart").click();
		Log.logInfo("点击首页的购物车按钮");
		// 进入购物车后，点击去结算按钮
		tp.getElement("submitBtn").click();
		Log.logInfo("进入购物车后，点击去结算按钮");
	}
	public void gotoShoppingCartSP(ToPayOrderPage tp) {
	
		// 进入购物车后，点击去结算按钮
		tp.getElement("submitBtn").click();
		Log.logInfo("进入购物车后，点击去结算按钮");
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

	// 打开首页
	public void openURL() {
		try {
			this.goTo(Config.pathURL);
		} catch (Exception e) {
			Log.logInfo("[购物车]  打开页面失败");
			Assert.fail("[购物车]  打开页面失败");
		}
	}

	// 检查默认收货地址图标
	public void checkDefaultAddress(ToPayOrderPage tp) {
		String defaultText = tp.getElement("defaultAddress").getText();
		if (defaultText.equals("默认")) {
			Log.logInfo("校验默认收货人的图标样式 测试场景通过");
			screen.snapshot((TakesScreenshot) driver, timeString, "订单确认页面",
					"订单确认页面", "默认收货地址图标检查");
			Assert.assertTrue(true);

		} else {
			Log.logInfo("校验默认收货人的图标样式 测试场景不通过");
			Assert.fail();
		}

	}

	// 检查无收货地址展示
	public void checkNoAddress(ToPayOrderPage tp) {
		String noAddress = tp.getElement("reciverInfo").getText();
		if (noAddress.equals("请选择收货地址")) {
			Log.logInfo("检验无收获地址显示 测试场景通过");
			screen.snapshot((TakesScreenshot) driver, timeString, "订单确认页面",
					"订单确认页面", "检验无收获地址显示");
			Assert.assertTrue(true);

		} else {
			Log.logInfo("检验无收获地址显示 测试场景不通过");
			Assert.fail();
		}
	}

	// 新增收货地址方法-收货地址选择页
	public void addressAddForList(ToPayOrderPage tp, Map<String, String> param) {
		// 点击订单确认页的收货地址框
		tp.getElement("addressLink").click();
		Log.logInfo("点击订单确认页的收货地址框");
		wait(3000);
		// 点击添加收货地址按钮
		tp.getElement("addressAdd").click();
		Log.logInfo("点击添加收货地址按钮");
		wait(3000);
		// 输入收件人、电话等
		tp.getElement("reciverName").sendKeys(param.get("reciverName"));
		Log.logInfo("输入收件人= " + param.get("reciverName"));
		tp.getElement("reciverPhone").sendKeys(param.get("reciverPhone"));
		Log.logInfo("输入收件人电话= " + param.get("reciverPhone"));
		wait(3000);
		this.getValueFromSelect(tp, "provinceId", param.get("provinceId"));
		Log.logInfo("选择省份=" + param.get("provinceId"));
		this.getValueFromSelect(tp, "cityId", param.get("cityId"));
		Log.logInfo("选择城市=" + param.get("cityId"));
		this.getValueFromSelect(tp, "districtId", param.get("districtId"));
		Log.logInfo("选择区县=" + param.get("districtId"));
		tp.getElement("address").sendKeys(param.get("address"));
		Log.logInfo("输入具体收货地址=" + param.get("address"));
		tp.getElement("addressSubmit").click();
		Log.logInfo("点击新增收货地址按钮");
		// 点击新增后，返回订单确认页，地址变为新增的地址
	}

	// 新增收货地址方法-管理页
	public void addressAddForManager(ToPayOrderPage tp,
			Map<String, String> param) {
		// 点击订单确认页的收货地址框
		tp.getElement("addressLink").click();
		Log.logInfo("点击订单确认页的收货地址框");
		wait(3000);
		// 管理按钮
		tp.getElement("addrManager").click();
		Log.logInfo("点击管理按钮");
		wait(3000);
		// 点击添加收货地址按钮
		tp.getElement("addAddrButton").click();
		Log.logInfo("点击添加收货地址按钮");
		wait(3000);
		// 输入收件人、电话等
		tp.getElement("reciverName").sendKeys(param.get("reciverName"));
		Log.logInfo("输入收件人= " + param.get("reciverName"));
		tp.getElement("reciverPhone").sendKeys(param.get("reciverPhone"));
		Log.logInfo("输入收件人电话= " + param.get("reciverPhone"));
		wait(3000);
		this.getValueFromSelect(tp, "provinceId", param.get("provinceId"));
		Log.logInfo("选择省份=" + param.get("provinceId"));
		this.getValueFromSelect(tp, "cityId", param.get("cityId"));
		Log.logInfo("选择城市=" + param.get("cityId"));
		this.getValueFromSelect(tp, "districtId", param.get("districtId"));
		Log.logInfo("选择区县=" + param.get("districtId"));
		tp.getElement("address").sendKeys(param.get("address"));
		Log.logInfo("输入具体收货地址=" + param.get("address"));
		tp.getElement("addressSubmit").click();
		Log.logInfo("点击新增收货地址按钮");
		// 点击新增后，返回订单确认页，地址变为新增的地址

	}

	public void getIntoView(WebElement el) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();", el);
	}

	public void isAddSuccess(ToPayOrderPage tp, Map<String, String> param) {
		this.getIntoView(tp.getElement("reciverInfo"));
		String receiver = tp.getElement("receiverNameText").getText();
		String phone = tp.getElement("lab_orderreceiverMphone").getText();
		Log.logInfo("收件人= " + receiver);
		Log.logInfo("手机= " + phone);
		if (receiver.equals(param.get("reciverName"))
				&& phone.equals(param.get("reciverPhone"))) {
			Log.logInfo("校验新增收货地址 测试场景通过");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("校验新增收货地址 测试场景不通过");
			Assert.fail();
		}
	}

	public void getValueFromSelect(ToPayOrderPage tp, String xpath, String value) {
		Select select = new Select(tp.getElementNoWait(xpath));
		select.selectByVisibleText(value);
	}

	private void addNoReceiverNameCheck(ToPayOrderPage tp,
			Map<String, String> param) {

		tp.getElement("addressLink").click();
		wait(3000);
		tp.getElement("addressAdd").click();
		wait(3000);
		tp.getElement("addressSubmit").click();
		String msgContext = tp.getElement("msgContext").getText();
		if (msgContext.equals("请输入收货人姓名")) {
			Log.logInfo("不填写用户名，直接点击提交按钮提示： " + msgContext);
			Log.logInfo("校验新增收货地址收货人不填写错误提示语  测试用例通过");
			Assert.assertTrue(true);
		} else {
			Log.logInfo("校验新增收货地址收货人不填写错误提示语  测试用例不通过");
			Assert.fail();
		}

	}

}
