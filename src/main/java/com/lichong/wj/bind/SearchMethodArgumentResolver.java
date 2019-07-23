package com.lichong.wj.bind;

import com.lichong.wj.bind.enums.SearchOperator;
import com.lichong.wj.bind.search.Search;
import com.lichong.wj.bind.searchFilter.SearchFilter;
import com.lichong.wj.exception.errorType.SearchException;
import com.lichong.wj.util.StringUtil;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;
import java.util.Map.Entry;

/**
 * 自定义参数解析器 封装 search 对象参数 以及 分页和排序
 * 2018年1月1日
 * @author cyg
 */
public class SearchMethodArgumentResolver extends BaseSearchMethodArgumentResolver implements HandlerMethodArgumentResolver {


	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return Search.class.equals(parameter.getParameterType());
	}
 
	/**
	 * 参考示例：
	 * 默认：
	 * 	search.name.eq
	 * 	search.age.eq
	 * 	生成sql=> and name = 张三 and age = 23
	 * 
	 * 带or关系 单个
	 *  search.name.eq
	 *  search.age.eq
	 *  search.id.eq_or
	 * 生成sql=> and name = '张三' and age = 23 or (id = 123456)
	 * 
	 * 带or关系 多个
	 *  search.name.eq
	 *  search.age.eq_or 
	 *  search.id.eq_or
	 * 生成sql=> and name = '张三' and (age = 23 or id = 123456) 
	 */
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Search search = new Search();
		Map<String, String[]> map = webRequest.getParameterMap();
		if(!map.isEmpty()) {
			for (Entry<String, String[]> entry : map.entrySet()) {
				String key = entry.getKey();
				Object value = this.transformValue(entry.getValue());
				if(key.contains(PREFIX) && value!=null && StringUtil.isNotBlank(value.toString().trim())) {
					String[] nameArr = key.split("\\.");
					if(nameArr.length == KEY_SIZE) {
						SearchFilter searchFilter = new SearchFilter();
						searchFilter.setValue(handlerLikeValue(value, SearchOperator.valueBySymbol(nameArr[2])));
						searchFilter.setOperator(SearchOperator.valueBySymbol(nameArr[2]));
						searchFilter.setKey(key);
						searchFilter.setSearchProperty(nameArr[1]);
						search.getSearchFilters().add(searchFilter);
					}else {
						throw new SearchException("搜索参数格式错误,参照 search.name.eq,缺一不可");
					}
				}
			}
		}
		Pageable pageable = pageableResolveArgument(parameter, mavContainer, webRequest, binderFactory);
		search.setPage(pageable);
		search.setSort(pageable.getSort());
		return search;
	}
	
	private Object handlerLikeValue(Object value,SearchOperator operator){
		if(null == value) {
			return "";
		}else if("true".equals(value)){
			return Boolean.TRUE;
		}else if("false".equals(value)){
			return Boolean.FALSE;
		}
		// like 操作符
		boolean like = operator.name().equals(SearchOperator.like.name());
		if(like) {
			return "%"+value.toString()+"%";
		}
		return value;
	}
	
}
