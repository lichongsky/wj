package com.lichong.wj.bind.searchFilter;

/**
 * 查询过滤器 抽象类
 * 2018年1月2日
 * @author cyg
 */
public interface SearchFilterInterface {

	/**
	 * 获取查询过滤jpql
	 */
	String getSearchFilterJpql(String tableAlias);
	
	/**
	 * 获取 OR 或的关系 jpql
	 */
	String getOrSearchFilterJpql(String tableAlias);
}
