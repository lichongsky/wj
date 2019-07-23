package com.lichong.wj.util;

import com.lichong.wj.exception.errorType.ServiceException;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description  关于数值工具类
 * @author cyg
 * @date 2018年3月8日下午5:03:40
 */
public class NumberUtil {
	
	private static final String NUMBER_REGEX = "[0-9]*";

	
	/**
	 * Double保留2位小数位
	 */
	public static Double toFixed(Double double1){
		double f1=0.00;
		try {
			if(double1!= null){
				BigDecimal bg = new BigDecimal(double1);  
				f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
		} catch (Exception e) {
			throw new ServiceException("转换小数位出错");
		}
		return f1;			
	}
	/**
	 * 字符串转 int
	 */
	public static Integer stringToInt(String string){
		Integer f1 = 0;
		try {
			f1 = Integer.parseInt(string);
		} catch (Exception e) {
			throw new  ServiceException("字符串转 int 出错");
		} 
		return f1;
	}
	
	/**
	 * 字符串转 浮点型
	 */
	public static Float stringToFloat(String string){
		Float f1 = 0f;
		try {
			f1 = Float.parseFloat(string);
		} catch (Exception e) {
			throw new  ServiceException("字符串转 Float 出错");
		}
		return f1;	
	}
	
	/**
	 * 字符串转double
	 */
	public static Double stringToDouble(String string){
		Double f1 = 0d;
		try {
			f1 = Double.parseDouble(string);
		} catch (Exception e) {
			throw new  ServiceException("字符串转 double 出错");
		}
		return f1;	
	}

	/**百分比 转 double
	 * 12%》》0.12
	 * */
	public static Double bfbToDouble(String string){
		Double f1 = 0d;
		try {
			string = string.substring(0,string.length() - 1);
			f1 = Double.parseDouble(string) / 100 ;
		} catch (Exception ignore) {
			throw new  ServiceException("百分比 转 double 出错");
		}
		return f1;
	}
	/**
	 * 正则验证字符串 是否是number
	 */
	public static boolean isNumber(String str){
	   Pattern pattern = Pattern.compile(NUMBER_REGEX); 
	   Matcher isNum = pattern.matcher(str);
	   if( !isNum.matches() ){
	       return false; 
	   } 
	   return true; 
	}

}
