package cn.cuit.utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class toJsonUtils {
	public static JSONObject toJSON(HttpServletRequest req) throws IOException {
		BufferedReader br= req.getReader();
	    String str, wholeStr = "";
	    while((str = br.readLine()) != null){
	        wholeStr += str;
		}
		System.out.println("refund:"+wholeStr);
	    JSONObject json=JSON.parseObject(wholeStr);
		return json;
	}
}
