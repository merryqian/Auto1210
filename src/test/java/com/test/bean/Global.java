package com.test.bean;

import java.util.Map;

import com.test.util.ParseXml;

public class Global {

	public static Map<String, String> global;

	static {
		ParseXml px = new ParseXml();
		String path=System.getProperty("user.dir");
		path=path+"/src/main/resources/";
		px.load(path+"test-data/global.xml");
		global = px.getChildrenInfoByElement(px.getElementObject("/*"));
	}

}
