package com.lichong.wj.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author ：lichongsky
 * @date ：Created in 2019/7/23 11:24
 */
@Data
@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    int articalId;
    String content;
    Date publishDate;
    int userId;
}
