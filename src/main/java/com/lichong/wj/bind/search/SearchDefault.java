package com.lichong.wj.bind.search;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * 默认的search
 * 2018年1月6日
 * @author cyg
 */
public class SearchDefault implements SearchFinal{

	/**
	 * 获取默认的Pageable
	 */
	public Pageable getDefaultPageable() {
		return new PageRequest(DEFAULT_PAGE, DEFAULT_LIMIT,this.getDefaultSort());
	}
	
	/**
	 * 获取默认的Sort
	 */
	public Sort getDefaultSort() {
		return new Sort(Direction.DESC,CREATETIME);
	}
}
