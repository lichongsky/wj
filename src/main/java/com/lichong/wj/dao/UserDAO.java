package com.lichong.wj.dao;

import com.lichong.wj.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUsername(String uerName);

    User getByUsernameAndPassword(String userName, String passWord);

}
