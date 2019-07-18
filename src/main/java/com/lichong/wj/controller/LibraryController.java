package com.lichong.wj.controller;

import com.lichong.wj.entity.Book;
import com.lichong.wj.entity.Category;
import com.lichong.wj.entity.Search;
import com.lichong.wj.service.BookService;
import com.lichong.wj.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    BookService bookService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/api/books")
    public List<Book> list() throws Exception {
        List<Book> list = bookService.list();
        return list;
    }


    @PostMapping("/api/addOrUpdate")
    public Book addOrUpdate(@RequestBody Book book) throws Exception {
        bookService.addOrUpdate(book);
        return book;
    }

    @PostMapping("/api/delete")
    public void delete(@RequestBody Book book) throws Exception {
        bookService.deleteById(book.getId());
    }

    @GetMapping("/api/listCategories")
    public List<Category> listCategories() throws Exception {
        return categoryService.list();
    }

    @GetMapping("/api/categories/{cid}/books")
    public List<Book> listByCategory(@PathVariable("cid") int cid) throws Exception {
        if (0 != cid) {
            return bookService.listByCategory(cid);
        } else {
            return list();
        }
    }


    @PostMapping("/api/search")
    public List<Book> searchResult(@RequestBody Search s) throws Exception {
        // 关键字为空时查询所有书籍
        if ("".equals(s.getKeywords())) {
            return bookService.list();
        } else {
            return bookService.Search(s.getKeywords());
        }
    }
}
