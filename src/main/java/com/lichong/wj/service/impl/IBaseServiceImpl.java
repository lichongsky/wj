package com.lichong.wj.service.impl;

import com.lichong.wj.bind.search.Search;
import com.lichong.wj.bind.specification.MySpecification;
import com.lichong.wj.dao.BaseRepository;
import com.lichong.wj.entity.response.DataGrid;
import com.lichong.wj.exception.errorType.BaseException;
import com.lichong.wj.service.IBaseService;
import com.lichong.wj.util.ReflectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：lichongsky
 * @date ：Created in 2019/7/23 16:19
 */
public class IBaseServiceImpl<M> implements IBaseService<M> {

    private static final String FIND_JPQL = "select x from %s x where 1=1 ";
    private static final String DELETE_JPQL = " delete from %s x where 1=1 ";
    private static final String COUNT_JPQL = " select count(x) from %s x where 1=1 ";
    protected final Class<M> entityClass;
    protected String entityName;
    protected String voName;
    protected String findJPQL;
    protected String countJPQL;
    protected String deleteJPQL;

    /**
     * 不能直接调用这个 使用getEntityManager()获取
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 获取实体管理者
     */
    public EntityManager getEntityManager() {
        if(entityManager.isOpen()) {
            return entityManager;
        }else {
            throw new BaseException("entityManager 已经关闭了不可以使用了");
        }
    }


    /**
     * 初始化
     */
    public IBaseServiceImpl(){
        this.entityClass = ReflectUtil.findParameterizedType(getClass(), 0);
        this.entityName = entityClass.getName();
        this.findJPQL = String.format(FIND_JPQL, entityName);
        this.countJPQL = String.format(COUNT_JPQL, entityName);
        this.deleteJPQL = String.format(DELETE_JPQL, entityName);
    }

    @Autowired
    private BaseRepository<M> repository;
    /**
     * 验证是不是我们要的领域类型 不是直接报异常
     */
    public BaseRepository<M> getRepository() throws BaseException {
        boolean b = repository.support(entityName);
        if(!b) {
            throw new BaseException("没有对应的领域类型");
        }
        return repository;
    }

    /***************************************保存***************************************************/
    @Override
    public M save(M m) {
        return getRepository().save(m);
    }

    @Override
    public M saveAndFlush(M m) {
        return getRepository().saveAndFlush(m);
    }

    @Override
    public List<M> batchSave(List<M> m) {
        return getRepository().save(m);
    }

    /***************************************删除***************************************************/
    //单个删除实体 必须存在ID
    @Override
    public void delete(M m) {
        getRepository().delete(m);
    }

    //根据主键删除
    @Override
    public void delete(Integer id) {
        getRepository().delete(id);
    }
    //删除所有的
    @Override
    public void deleteAll() {
        getRepository().deleteAllInBatch();
    }

    //批量删除
    @Override
    public void batchDeleteIds(List<Integer> ids) {
        String jpql = deleteJPQL + " and x.id in (:ids) ";
        getEntityManager().createQuery(jpql)
                .setParameter("ids", ids)
                .executeUpdate();
    }

    @Override
    public void batchDeleteEntity(List<M> ids) {
        getRepository().deleteInBatch(ids);
    }

    /***************************************修改***************************************************/
    //修改
    @Override
    public M update(M m) {
        return getEntityManager().merge(m);
    }

    //批量修改
    @Override
    public List<M> batchUpdate(List<M> ms) {
        List<M> result = new ArrayList<M>();
        for (M m : ms) {
            result.add(this.update(m));
        }
        return result;
    }

    /***************************************查询单个实体***************************************************/
    @Override
    public M findOne(Integer id) {
        return (M) getRepository().getOne(id);
    }

    @SuppressWarnings("unchecked")
    //获取单个实体
    @Override
    public M findOne(Specification<M> spec) {
        return (M) getRepository().findOne(spec);
    }

    //获取单个实体
    @Override
    public M findOne(Search search) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> queryOneMapByJpql(String jpql, Map<String, Object> maps) {
        return (Map<String, Object>) getEntityManager()
                .createQuery(jpql)
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public M queryOneBySql(String sql, Class<M> clas, Map<String, Object> maps) {
        return (M) getEntityManager()
                .createNativeQuery(sql,clas)
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public M queryOneBySql(String sql, Map<String, Object> maps) {
        return (M) getEntityManager()
                .createNativeQuery(sql)
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> queryOneMapBySql(String sql, Map<String, Object> maps) {
        return (Map<String, Object>) getEntityManager()
                .createNativeQuery(sql)
                .getSingleResult();
    }

    /***************************************查询多个实体***************************************************/
    //获取所有的
    @Override
    public List<M> findAll() {
        return getRepository().findAll();
    }
    @Override
    public List<M> findAll(List<Serializable> ids) {
        return getRepository().findAll(ids);
    }
    @Override
    public List<M> findAll(Sort sort) {
        return getRepository().findAll(sort);
    }
    @Override
    public List<M> findAll(Specification<M> spec,Sort sort) {
        return getRepository().findAll(spec, sort);
    }
    @Override
    public List<M> findAll(Specification<M> spec) {
        return getRepository().findAll(spec);
    }
    @Override
    public List<M> findAll(Search search,Sort sort) {
        return getRepository().findAll(new MySpecification<M>(search).toPredicate(),sort);
    }
    @Override
    public List<M> findAll(Search search) {
        return getRepository().findAll(new MySpecification<M>(search).toPredicate());
    }
    @Override
    public DataGrid findPage(Search search) {
        Page<M> page = getRepository().findAll(new MySpecification<M>(search).toPredicate(),search.getPage());
        return new DataGrid(page.getTotalElements()	,page.getContent());
    }

    /***************************************获取条数***************************************************/
    //获取该实体条数
    @Override
    public Long getCount() {
        return getRepository().count();
    }

    //根据Specification对象获取条数
    @Override
    public Long getCount(Specification<M> specification) {
        return getRepository().count(specification);
    }

    //根据Search对象获取条数
    @Override
    public Long getCount(Search search) {
        return getRepository().count(new MySpecification<M>(search).toPredicate());
    }

    /*******************************************entity***********************************************/
    @Override
    public Boolean exists(String id) {
        return null;
    }

    @Override
    public Boolean exists(Search search) {
        return null;
    }
}
