package com.lichong.wj.bind;

import com.lichong.wj.bind.search.SearchFinal;
import com.lichong.wj.util.ArrayUtils;
import com.lichong.wj.util.StringUtil;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

/**
 * 绑定 search 对象参数,提供 分页排序 参数绑定
 * 2018年1月2日
 * @author cyg
 */
public class BaseSearchMethodArgumentResolver implements SearchFinal {


	//分页 排序
	protected Pageable pageableResolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
		Map<String, String[]> map = webRequest.getParameterMap();
		
		Integer size = getPageParam(map, LIMIT, DEFAULT_LIMIT);
		Integer page = getPageParam(map, PAGE, DEFAULT_PAGE);
		if(page > 0) {
			page = page -1;
		}
		Sort sort = this.getSort(map);
		PageRequest pageRequest = new PageRequest(page,size,sort);
		return pageRequest;
	}  
	
	//获取分页参数。没有则使用默认的
	protected int getPageParam(Map<String, String[]> map,String key,int defaultValue) {
		String[] values = map.get(key);
		if(values == null || values.length == 0) {
			return defaultValue;
		}else {
			if(StringUtil.isBlank(values[0])) {
				return defaultValue;
			}
			return Integer.parseInt(values[0]);
		}
	}
	
	//获取排序 没有则使用默认排序 按createTime来
	protected Sort getSort(Map<String, String[]> map) {
		String sort = getSortParam(map, SORT, CREATETIME);
		String order = getSortParam(map, ORDER, DESC); 
		if(order.toLowerCase().equals(DESC.toLowerCase())) {
			return new Sort(Direction.DESC,sort);
		} else {
			return new Sort(Direction.ASC,sort);
		}
	}
	
	//获取排序参数没有使用默认值
	protected String getSortParam(Map<String, String[]> map,String key,String defaultValue){
		String[] sort = map.get(key);
		if(sort == null || sort.length == 0) {
			return defaultValue;
		}else {
			if(StringUtil.isBlank(sort[0])) {
				return defaultValue;
			}
			return sort[0];
		}
	}
	
	//转换数组to List
	protected Object transformValue(String[] vals) {
		 if(vals!=null&&vals.length>0) {
			 if(vals.length > 1){
				 return ArrayUtils.arrayToList(vals);
			 } else {
				 return vals[0];
			 }
		 }
		 return null;
	}
}