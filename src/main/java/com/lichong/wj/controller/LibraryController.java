package com.lichong.wj.controller;

import com.lichong.wj.bind.search.Search;
import com.lichong.wj.entity.Book;
import com.lichong.wj.entity.Category;
import com.lichong.wj.entity.SearchBy;
import com.lichong.wj.service.impl.BookServiceImpl;
import com.lichong.wj.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    BookServiceImpl bookServiceImpl;
    @Autowired
    CategoryServiceImpl categoryServiceImpl;

    @GetMapping("/api/books")
    public List<Book> list() throws Exception {
        List<Book> list = bookServiceImpl.list();
        return list;
    }


    @PostMapping("/api/addOrUpdate")
    public Book addOrUpdate(@RequestBody Book book) throws Exception {
        bookServiceImpl.addOrUpdate(book);
        return book;
    }

    @PostMapping("/api/delete")
    public void delete(@RequestBody Book book) throws Exception {
        bookServiceImpl.deleteById(book.getId());
    }

    @GetMapping("/api/listCategories")
    public List<Category> listCategories() throws Exception {
        return categoryServiceImpl.list();
    }

    @GetMapping("/api/categories/{cid}/books")
    public List<Book> listByCategory(@PathVariable("cid") int cid) throws Exception {
        if (0 != cid) {
            return bookServiceImpl.listByCategory(cid);
        } else {
            return list();
        }
    }


    @PostMapping("/api/search")
    public List<Book> searchResult(@RequestBody SearchBy s) throws Exception {
        // 关键字为空时查询所有书籍
        if ("".equals(s.getKeywords())) {
            return bookServiceImpl.list();
        } else {
            return bookServiceImpl.Search(s.getKeywords());
        }
    }
}
