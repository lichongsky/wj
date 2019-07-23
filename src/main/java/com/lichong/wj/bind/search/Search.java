package com.lichong.wj.bind.search;

import com.lichong.wj.bind.enums.SearchOperator;
import com.lichong.wj.bind.searchFilter.SearchFilter;
import com.lichong.wj.exception.errorType.SearchException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接收参数 对象
 * 2018年1月3日
 * @author cyg
 */
@Getter
@Setter
@ToString
public class Search extends SearchDefault implements SearchInterface {

	
	private static final Logger log = LoggerFactory.getLogger(Search.class);
	
	private static final long serialVersionUID = 1L;

	private Pageable page;

	private Sort sort;
	
	public static final String TABLE_ALIAS = "t."; 
	
	//接受页面参数只会封装到这个filter里面
	//示例：where 1=1 and x.name=张三 and x.age=23    都是且的关系
	private List<SearchFilter> searchFilters = new ArrayList<>();
	
	//添加or 关系 filter 
	//示例：where 1=1 and x.name=张三 or x.age=23 只有一个 可以这样使用
	//示例：where 1=1 and x.name=张三 and（x.age=23 or x.age=22） 多个 可以这样使用
	private List<SearchFilter> orSearchFilters = new ArrayList<>();

	//装载的时参数名称和值 name,value,来自于searchFilters和orSearchFilters 
	//只能获取paramMap 添加通过Search.addSearchaFilter().
	//默认采用 :name 
	private Map<String, Object> paramMap = new HashMap<String, Object>();
	
	/**
	 * 默认构造器 初始化 Pageable Sort 使用默认值
	 */
	public Search() {
		 this.page = getDefaultPageable();
		 this.sort = getDefaultSort();
	}
	
	/**
	 * 获取paramMap
	 */
	public Map<String, Object> getParamMap() {
		if(this.paramMap.size() > 0) {
			this.paramMap.clear();
		}
		for (SearchFilter filter: searchFilters) {
			this.paramMap.put(filter.getSearchProperty(), filter.getValue());
		}
		for (SearchFilter filter : orSearchFilters) {
			this.paramMap.put(filter.getSearchProperty(), filter.getValue());
		}
		return paramMap;
	}
	
	@Override
	public String getSearchFilterJpql() {
		StringBuffer jpql = new StringBuffer();
		for (SearchFilter filter : searchFilters) {
			jpql.append(filter.getSearchFilterJpql(TABLE_ALIAS));
		}
		StringBuffer orSearchFilterJpql = this.getOrSearchFilterJpql();
		return jpql.append(orSearchFilterJpql).toString();
	}
	
	//拼装 or 条件
	private StringBuffer getOrSearchFilterJpql() {
		StringBuffer or = new StringBuffer();
		if(orSearchFilters.size() ==0) {
			return or;
		}
		if(orSearchFilters.size() ==1) {
			or.append(" OR ( %s )");
			StringBuffer buffer = new StringBuffer(String.format(or.toString(), orSearchFilters.get(0).getOrSearchFilterJpql(TABLE_ALIAS)));
			if(searchFilters.size()<=0) {
				log.error("无意义的sql语句:where 1=1 {}",buffer.toString());
				throw new SearchException("该过滤条件无实质性查询意义");
			}
			return buffer;
		}else {
			or.append(" AND ( ");
			for (SearchFilter filter : orSearchFilters) {
				or.append(filter.getOrSearchFilterJpql(TABLE_ALIAS)+" OR ");
			}
			int i = or.lastIndexOf("OR");
			or.replace(i, i+2, "");
			return or.append(" ) ");
		}
		
	}
	
	@Override
	public String getSearchSortJpql() {
		StringBuffer jpql = new StringBuffer(" ORDER BY ");
		sort.forEach((sort)->{
			jpql.append(TABLE_ALIAS+sort.getProperty()+" ");
			jpql.append(sort.getDirection().name().toUpperCase());
		});
		return jpql.toString();
	}
	/**
	 * LIMIT mysql 换数据库会有问题
	 */
	@Override
	public String getSearchPageJpql() {
		StringBuffer jpql = new StringBuffer(" LIMIT %s,%s ");
		int offset = page.getOffset();
		int limit = page.getPageSize();
		return String.format(jpql.toString(), offset,limit);
	}
	
	@Override
	public String getAllJpql() {
		String filterJpql = this.getSearchFilterJpql();
		String pageJpql = this.getSearchPageJpql();
		String sortJpql = this.getSearchSortJpql();
		return filterJpql  + sortJpql + pageJpql;
	}
	
	@Override
	public String getFilterAndSortJpql() {
		return this.getSearchFilterJpql() + this.getSearchSortJpql();
	}

	@Override
	public String getFilterAndPageJpql() {
		return this.getSearchFilterJpql() + this.getSearchPageJpql();
	}

	@Override
	public String getPageAndSortJpql() {
		return this.getSearchSortJpql() + this.getSearchPageJpql();
	}
	
	@Override
	public void addSearchaFilter(String searchProperty, SearchOperator searchOperator, Object value) {
		this.searchFilters.add(new SearchFilter(searchProperty, searchOperator, value));
	}
	
	@Override
	public void addOrSearchaFilter(String searchProperty, SearchOperator operator, Object value) {
		this.orSearchFilters.add(new SearchFilter(searchProperty, operator, value));
	}
}
