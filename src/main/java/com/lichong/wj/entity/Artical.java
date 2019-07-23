package com.lichong.wj.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author ：lichongsky
 * @date ：Created in 2019/7/23 11:03
 */

@Data
@Entity
@Table(name = "artical")
public class Artical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String title;
    String mdContent;
    String htmlContent;
    String summary;
    int cid;
    int uid;
    Date publishDate;
    Date editDate;
    int state;
    int pageView;
}
