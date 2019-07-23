package com.lichong.wj.service;

import com.lichong.wj.bind.search.Search;
import com.lichong.wj.entity.response.DataGrid;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author ：lichongsky
 * @date ：Created in 2019/7/23 16:16
 */
public interface IBaseService<M> {
    M save(M m);
    M saveAndFlush(M m);
    List<M> batchSave(List<M> m);

    void delete(M m);
    void delete(Integer id);
    void deleteAll();
    void batchDeleteIds(List<Integer> ids);
    void batchDeleteEntity(List<M> ids);

    M update(M m);
    List<M> batchUpdate(List<M> m);


    M findOne(Integer id);
    M findOne(Specification<M> spec);
    M findOne(Search search);
    Map<String, Object> queryOneMapByJpql(String jpql, Map<String, Object> maps);
    M queryOneBySql(String sql,Class<M> clas,Map<String, Object> maps);
    M queryOneBySql(String sql,Map<String, Object> maps);
    Map<String, Object> queryOneMapBySql(String sql,Map<String, Object> maps);


    /**
     * 查询多个数据
     */
    List<M> findAll();
    List<M> findAll(List<Serializable> ids);
    List<M> findAll(Sort sort);
    List<M> findAll(Specification<M> spec,Sort sort);
    List<M> findAll(Specification<M> spec);
    List<M> findAll(Search search);
    List<M> findAll(Search search, Sort sort);
    DataGrid findPage(Search search);

    /**
     * 获取条数
     */
    Long getCount();
    Long getCount(Search search);
    Long getCount(Specification<M> specification);

    Boolean exists(String id);
    Boolean exists(Search search);
}
