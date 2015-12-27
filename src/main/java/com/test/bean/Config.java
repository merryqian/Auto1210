package com.test.bean;


import org.testng.annotations.Test;

import com.test.util.ParseXml;


public class Config {
	public static String browser;
	public static int waitTime;
	public static String pathURL;
	static {
		ParseXml px = new ParseXml();
		String path=System.getProperty("user.dir");
		path=path+"/src/main/resources";
		px.load(path+"/Config/config.xml");
		browser = px.getElementText("/config/browser");
		waitTime = Integer.valueOf(px.getElementText("/config/waitTime"));
		pathURL = px.getElementText("/config/path");
		
		
	}
	@Test
	public void test1()
	{
		System.out.println(browser);
		System.out.println(waitTime);
		System.out.println(pathURL);
	}


}
