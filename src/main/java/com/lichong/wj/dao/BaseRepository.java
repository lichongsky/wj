package com.lichong.wj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 数据访问 抽象接口
 *
 * @ClassName: BaseRepository
 */
@NoRepositoryBean
public interface BaseRepository<M> extends JpaRepository<M, Serializable>,JpaSpecificationExecutor<M> {

    /**
     * 验证是不是我们要的领域模型
     */
    boolean support(String modelType);

}
