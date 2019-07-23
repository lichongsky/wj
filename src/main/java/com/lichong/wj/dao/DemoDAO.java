package com.lichong.wj.dao;

import com.lichong.wj.entity.Demo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author ：lichongsky
 * @date ：Created in 2019/7/23 13:57
 */
public interface DemoDAO extends JpaRepository<Demo, Integer> {
    @Query(value = "SELECT * FROM demo_table order by reg_date",
            countQuery = "SELECT count(*) FROM demo_table",
            nativeQuery = true)
    Page<Demo> findAllOrOrderByRegDate(Pageable pageable);

    @Override
    List<Demo> findAll();

    @Override
    <S extends Demo> Optional<S> findOne(Example<S> example);
}
