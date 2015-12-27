package com.test.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {
	static String result="";
	public static String doPost(String url,String params){
		URL urlPost = null;
		HttpURLConnection http = null;
		
		try {
			urlPost = new URL(url);
			http = (HttpURLConnection)urlPost.openConnection();
			http.setDoInput(true);
			http.setDoOutput(true);
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/json");
			http.setRequestProperty("Charset", "UTF-8");
			http.connect();
			
			OutputStreamWriter out = new OutputStreamWriter(http.getOutputStream(),"utf-8");
			out.write(params);
			out.flush();
			out.close();
			
			
			//System.out.println("responseCode="+http.getResponseCode()+"  responseMsg="+http.getResponseMessage());
			if(http.getResponseCode()==200){
				BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(),"utf-8"));
				String inputLine = "";
				while((inputLine=in.readLine())!=null){
					result += inputLine;
				}
				in.close();
			}
			
			System.out.println("result="+result);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return result;
	}
	public static boolean verifyResult()
	{
		boolean flag=false;
		if(result.contains("\"statusCode\":200"))
			System.out.println("成功");
		else System.out.println("失败");
		return flag;
	}
	
	public static void main(String[] args) {
		
		doPost("http://10.201.128.190:9081/iokblShoppingCartApi/cart/queryShoppingCart.htm","{\"memberId\":\"100000000000982\",\"member_token\":\"ABe3O8nwSWyzimeg-Ed13Q\",\"shoppingCartId\":\"43545454\",\"orderSourceCode\":\"1\"}");
		verifyResult();
	}
}
