package com.lichong.wj.bind.search;

import com.lichong.wj.bind.enums.SearchOperator;

import java.io.Serializable;

/**
 * 接收参数
 * 
 * @author cyg
 */
public interface SearchInterface extends Serializable{
 
	/**
	 * 添加 or 过滤条件
	 */
	void addOrSearchaFilter(String searchProperty, SearchOperator operator, Object value);
	
	/**
	 * 添加过滤条件
	 */
	void addSearchaFilter(String searchProperty, SearchOperator operator, Object value);
	
	/**
	 * 获取where 条件 包含分页 和 排序
	 */
	String getAllJpql();
	
	/**
	 * 获取where 条件 和 排序
	 */
	String getFilterAndSortJpql();
	
	/**
	 * 获取where 条件 包含分页 
	 */
	String getFilterAndPageJpql();
	
	/**
	 * 获取 分页 和 排序
	 */
	String getPageAndSortJpql();
	
	/**
	 * 只获取过滤条件
	 */
	String getSearchFilterJpql();
	
	/**
	 * 只获取排序条件
	 */
	String getSearchSortJpql();
	
	/**
	 * 只获取分页条件
	 */
	String getSearchPageJpql(); 
	
}
