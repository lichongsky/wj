package com.lichong.wj;

import com.lichong.wj.dao.DemoDAO;
import com.lichong.wj.entity.Book;
import com.lichong.wj.entity.Demo;
import com.lichong.wj.entity.response.DataGrid;
import com.lichong.wj.service.impl.BookServiceImpl;
import com.lichong.wj.service.impl.DemoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WjApplicationTests {

    @Autowired
    BookServiceImpl bookServiceImpl;

    @Autowired
    DemoDAO demoDAO;

    @Autowired
    DemoServiceImpl demoServiceImpl;
    @Test
    public void testEntity() {
        List<Book> list = bookServiceImpl.list();
        for (Book book : list) {
            System.out.println(book.toString());
        }
    }

    @Test
    public void testJPA1() {
        Page<Demo> allOrOrderByRegDate = demoDAO.findAllOrOrderByRegDate(Pageable.unpaged());
        int totalPages = allOrOrderByRegDate.getTotalPages();
        Long totalElement = allOrOrderByRegDate.getTotalElements();
        List<Demo> list = allOrOrderByRegDate.getContent();

        System.out.println();
    }
    @Test
    public void testDemoService(){
        DataGrid allByRegDate = demoServiceImpl.findAllByRegDate();

        List<Demo> list = (List<Demo>) allByRegDate.getRows();
        long totalElement = allByRegDate.getTotal();
        System.out.println(totalElement);
        for(Demo demo:list){
            System.out.println(demo.getName()+"-----"+demo.getAddress()+"-----"+demo.getRegDate());
        }

    }

}
