package com.lichong.wj.dao;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * 接口实现类 实现我们自定义的方法
* @ClassName: BaseRepositoryImpl 
* @Description: TODO
* @author pujiang
* @date 2018年6月8日 下午9:35:06 
* 
* @param <M>
* @param <I>
 */
public class BaseRepositoryImpl<M,I extends Serializable> 
			extends SimpleJpaRepository<M, Serializable> implements BaseRepository<M>{

	private final Class<M> domainClass;
	@SuppressWarnings("unused")
	private final EntityManager entityManager;
	
	public BaseRepositoryImpl(Class<M> domainClass, EntityManager em) {
		super(domainClass, em);
		this.domainClass = domainClass;
		this.entityManager = em;
	}

	@Override
    public boolean support(String modelType) {
        return domainClass.getName().equals(modelType);
    }

	
}
