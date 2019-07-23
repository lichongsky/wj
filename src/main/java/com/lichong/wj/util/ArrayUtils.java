package com.lichong.wj.util;

import java.util.Arrays;
import java.util.List;

/**
 * 数据utils
 * 2018年1月2日
 * @author cyg
 */
public class ArrayUtils {
 
	
	/**
	 * @description 数组 转 list
	 * @author cyg
	 * @date 2018年5月11日下午11:24:28
	 */
	public static List<String> arrayToList(String[] arr){
		return Arrays.asList(arr);
	}
	
	/**
	 * @description 数组 查询in操作符 jpql 语句
	 * @author cyg
	 * @date 2018年5月11日下午11:24:38
	 */
	public static String arrayToInJpql(List<String> ids){
		if(ids==null || ids.size()== 0) {
			return "()";
		}
		StringBuffer str = new StringBuffer("(");
		for (String s : ids) {
			str.append("'"+s+"',");
		}
		return str.toString().substring(0,str.length()-1)+")";
	}
}
