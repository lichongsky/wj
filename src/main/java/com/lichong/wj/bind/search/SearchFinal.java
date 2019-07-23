package com.lichong.wj.bind.search;

/**
 * search 常量
 * 2018年1月6日
 * @author cyg
 */
public interface SearchFinal {
	
	 final String PREFIX = "search.";//参数默认前缀
	 final  int KEY_SIZE = 3;//代表 search.name.eq>>>.spilt() == 3
	
	 final  String SORT = "sort";//接收前端参数排序字段 KEY 示例： person/findAll?sort=name&order=desc
	 final  String ORDER = "order";//接收前端参数排序方式 KEY 示例： person/findAll?sort=name&order=desc
	
	 final  String DESC = "DESC"; //降序
	 final  String ASC = "ASC";   //升序
	 final  String CREATETIME = "createTime";  //默认排序字段
	
	 final  String LIMIT = "limit";//每页显示大小 实例：person/findAll?sort=name&order=desc&limit=5&page=1
	 final  String PAGE = "page";//第几条开始 实例：person/findAll?sort=name&order=desc&limit=5&page=1
	
	 final  int DEFAULT_LIMIT = 10;//默认每页显示几条
	 //org.springframework.data.domain.PageRequest.PageRequest 这个实现类 默认页码从0页开始
	 final  int DEFAULT_PAGE = 0;//默认显示第一页

}
