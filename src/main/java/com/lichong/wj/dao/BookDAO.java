package com.lichong.wj.dao;

import com.lichong.wj.entity.Book;
import com.lichong.wj.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDAO extends JpaRepository<Book,Integer> {

    List<Book> findAllByCategory(Category category);

    List<Book> findAllByTitleLikeOrAuthorLike(String keyword1,String keyword2);
}
