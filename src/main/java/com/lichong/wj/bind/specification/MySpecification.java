package com.lichong.wj.bind.specification;

import com.lichong.wj.bind.search.Search;
import com.lichong.wj.bind.searchFilter.SearchFilter;
import com.lichong.wj.exception.errorType.SearchException;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * search 转 specifiction
 * 2018年1月3日
 * @author cyg
 */
public class MySpecification<M> extends BaseSpecification<M>{

	private Search search;
	 
	public MySpecification(Search search) {
		this.search = search;
	}
	
	/**
	 * 获取 search 转 specifiction 对象
	 */
	public Specification<M> toPredicate() {
		this.getSearchFilters();
		return new Specification<M>() {
			@Override
			public Predicate toPredicate(Root<M> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(search.getOrSearchFilters().size() == 0 && search.getSearchFilters().size() == 0) {
					return cb.conjunction();
				}
				Predicate predicate = getPredicate(root, cb,search.getSearchFilters(),AND);
				Predicate predicateOr = getPredicate(root, cb,search.getOrSearchFilters(),OR);
				if(predicate == null) {
					return  cb.or(predicateOr);
				}
				if(predicateOr == null) {
					return predicate;
				}
				if(predicateOr.getExpressions().size() == 1) {
					return cb.or(predicateOr,predicate);
				}
				return cb.and(predicateOr,predicate);
			}
		};
	}

	/**
	 * 获取search Filter
	 */
	private void getSearchFilters(){
		if(this.search == null) {
			throw new SearchException("缺少Search对象");
		}
		validSearchFilter(search.getSearchFilters());
	}
	
	/**
	 * 使用Specification查询时不能使用 ? 问号传参
	 */
	private void validSearchFilter(List<SearchFilter> searchFilters) {
		for (SearchFilter filter : searchFilters) {
			if("?".equals(filter.toString())) {
				throw new SearchException("使用Specification查询时不能使用 ? 内部已实现");
			}
		}
	}
	
	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	} 

	
}
