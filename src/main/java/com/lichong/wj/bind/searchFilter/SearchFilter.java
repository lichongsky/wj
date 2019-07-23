package com.lichong.wj.bind.searchFilter;

import com.lichong.wj.bind.enums.SearchOperator;
import com.lichong.wj.exception.errorType.SearchException;
import com.lichong.wj.util.ArrayUtils;
import com.lichong.wj.util.NumberUtil;
import lombok.Getter;
import lombok.Setter;


/**
 * 参数过滤对象
 * 2018年1月2日
 * @author cyg
 */
@Getter
@Setter
public class SearchFilter implements SearchFilterInterface{

	private String key;
	private String searchProperty;
	private SearchOperator operator;
	private Object value;
	
	/**
	 * 默认构造
	 */
	public SearchFilter() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 添加自定义过滤
	 */
	public SearchFilter(String searchProperty, SearchOperator operator, Object value) {
		this.searchProperty = searchProperty;
		this.operator = operator;
		this.value = value;
	}
	
	@Override
	public String getOrSearchFilterJpql(String tableAlias) {
		StringBuffer orJpql = new StringBuffer();
		orJpql.append(tableAlias+this.searchProperty+" ");
		orJpql.append(this.operator.getOperator()+" ");
		Object value = this.getValue();
		orJpql.append(this.handlerLikeValue(value));
		return orJpql.toString();
	}

	@Override
	public String getSearchFilterJpql(String tableAlias) {
		StringBuffer jpql = new StringBuffer();
		jpql.append(" AND ");
		jpql.append(tableAlias+this.searchProperty+" ");
		jpql.append(this.operator.getOperator()+" ");
		Object value = this.getValue();
		jpql.append(this.handlerLikeValue(value));
		return jpql.toString();
	}
	/**
	 * 处理特殊的查询操作符
	 * in 
	 * like
	 */
	private String handlerLikeValue(Object value) {
		if(null == value) {
			return "";
		}
		// in 操作符
		boolean in = this.operator.name().equals(SearchOperator.in.name());
		if(in) {
			if(value instanceof String) {
				String[] arr = value.toString().replace("[", "").replace("]", "").split(",");
				return ArrayUtils.arrayToInJpql(ArrayUtils.arrayToList(arr));
			}
			if(value instanceof String[]) {
				String[] arr = (String[]) value;
				return ArrayUtils.arrayToInJpql(ArrayUtils.arrayToList(arr));
			}
			throw new SearchException("查询in操作符出错！示例:String >[1,2] 或 String[] > [1,2]");
		}
		// number 加上单引号
		if(NumberUtil.isNumber(value.toString())) {
			return "'"+value+"'".toString();
		}
		// like 操作符
		boolean like = this.operator.name().equals(SearchOperator.like.name());
		if(like) {
			return "'%"+value.toString()+"%'";
		}
		return "'"+value.toString()+"'";
	}
	
}
