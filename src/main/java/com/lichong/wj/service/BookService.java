package com.lichong.wj.service;

import com.lichong.wj.dao.BookDAO;
import com.lichong.wj.dao.CategoryDAO;
import com.lichong.wj.entity.Book;
import com.lichong.wj.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookDAO bookDAO;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryDAO categoryDAO;

    /**
     * 查询
     * @return
     */
    public List<Book> list(){
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        return bookDAO.findAll(sort);
    }

    /**
     * 新增或修改 当主键存在时更新数据，当主键不存在时插入数据。
     * @param book
     */
    public void addOrUpdate(Book book){
        bookDAO.save(book);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteById(int id){
        bookDAO.deleteById(id);
    }

    /**
     * 通过cid查询所有book
     * @param cid
     * @return
     */
    public List<Book> listByCategory(int cid){
        Category category = categoryService.get(cid);
        return bookDAO.findAllByCategory(category);
    }

    /*public List<Book> listByCategory(int cid){
        Category category = categoryDAO.getOne(cid)
        return bookDAO.findAllByCategory(category);
    }*/

    public List<Book> Search(String keywords) {
        return bookDAO.findAllByTitleLikeOrAuthorLike('%' + keywords + '%', '%' + keywords + '%');
    }
}
