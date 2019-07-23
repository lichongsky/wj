package com.lichong.wj.bind.specification;

import com.lichong.wj.bind.enums.SearchOperator;
import com.lichong.wj.bind.searchFilter.SearchFilter;
import com.lichong.wj.exception.errorType.SearchException;
import org.hibernate.jpa.criteria.CriteriaBuilderImpl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 提供转换方法
 * 2018年1月3日
 * @author cyg
 */
public class BaseSpecification<M> {

	protected final static String AND="AND";
	protected  final static String OR="OR";
	
	protected Predicate getPredicate(Root<M> root, CriteriaBuilder cb, List<SearchFilter> filters, String type) {
		if(filters.size() <=0) {
			return null;
		}
		ArrayList<Predicate> ls = new ArrayList<Predicate>();
		for (SearchFilter filter : filters) {
			ls.add(this.filterToPredicate(filter,root,cb));
		}
		Predicate ps[]=new Predicate[ls.size()];
		for(int i=0,j=ls.size();i<j;i++){
			ps[i]=ls.get(i);
		}
		if(AND.equals(type)) {
			return cb.and(ps);			
		}else {
			return cb.or(ps);	
		}
	}
	
	/**
	 * SearchFilter to predicate 
	 */
	private Predicate filterToPredicate(SearchFilter filter,Root<M> root, CriteriaBuilder cb) {
		CriteriaBuilderImpl builderImpl = (CriteriaBuilderImpl) cb;
		String property = filter.getSearchProperty();
		SearchOperator operator = filter.getOperator();
		Object value = filter.getValue();
		Predicate predicate = null;
		switch (operator) {
		case eq:
			predicate = cb.equal(root.get(property), value);
			break;
		case ne:
			predicate = cb.notEqual(root.get(property), value);
		break;
		case gt:
			predicate = cb.gt(root.get(property), Integer.valueOf(value.toString()));
		break;
		case gte:
			predicate = cb.greaterThanOrEqualTo(root.get(property), Integer.valueOf(value.toString()));
		break;
		case lt:
			predicate = cb.lt(root.get(property), Integer.valueOf(value.toString()));
		break;
		case lte:
			predicate = cb.lessThanOrEqualTo(root.get(property), Integer.valueOf(value.toString()));
		break;
		case like:
			predicate = cb.like(root.get(property), value.toString());
		break;
		case notLike:
			predicate = cb.notLike(root.get(property), value.toString());
		break;
		case isNull:
			predicate = cb.isNull(root.get(property));
		break;
		case isNotNull:
			predicate = cb.isNotNull(root.get(property));
		break;
		case in:
			predicate = builderImpl.in(root.get(property), value);
		break;
		case notIn:
			predicate = builderImpl.not(builderImpl.in(root.get(property), value));
		break;
		default:
			throw new SearchException("没有对应的操作符，参照SearchOperator.java");
		}
		return predicate;
	}
	
	
	public BaseSpecification() {
		// TODO Auto-generated constructor stub
	} 
	
	
	
}
