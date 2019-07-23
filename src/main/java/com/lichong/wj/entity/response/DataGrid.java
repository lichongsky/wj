package com.lichong.wj.entity.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据承载对象
 * @author ：lichongsky
 * @date ：Created in 2019/7/23 14:36
 */
@Data
public class DataGrid implements Serializable {
	private static final long	serialVersionUID	= 1L;
	private Long total;
    public List<?> rows;
	public DataGrid() {
		
	}

	public DataGrid(List<?> rows) {
		this.rows = rows;
	}

	public DataGrid(Long total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}
}
