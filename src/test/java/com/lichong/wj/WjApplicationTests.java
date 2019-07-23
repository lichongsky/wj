package com.lichong.wj;

import com.lichong.wj.entity.Book;
import com.lichong.wj.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WjApplicationTests {

    @Autowired
    BookService bookService;
    @Test
    public void testEntity() {
        List<Book> list = bookService.list();
        for(Book book : list){
            System.out.println(book.toString());
        }
    }

}
