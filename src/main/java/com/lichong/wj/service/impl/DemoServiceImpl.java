package com.lichong.wj.service.impl;

import com.lichong.wj.dao.DemoDAO;
import com.lichong.wj.entity.Demo;
import com.lichong.wj.entity.response.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：lichongsky
 * @date ：Created in 2019/7/23 14:02
 */
@Service
public class DemoServiceImpl {

    @Autowired
    DemoDAO demoDAO;

    /**
     * order by reg_date 查询所有demo数据
     * @return
     */
    public DataGrid findAllByRegDate(){
        Page<Demo> allOrOrderByRegDate = demoDAO.findAllOrOrderByRegDate(Pageable.unpaged());
        DataGrid dataGrid = new DataGrid();
        dataGrid.setRows(allOrOrderByRegDate.getContent());
        dataGrid.setTotal(allOrOrderByRegDate.getTotalElements());
        return dataGrid;
    }

    public List<Demo> findAll(){
        return demoDAO.findAll();
    }

}
