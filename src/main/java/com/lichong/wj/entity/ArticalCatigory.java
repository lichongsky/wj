package com.lichong.wj.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author ：lichongsky
 * @date ：Created in 2019/7/23 11:12
 */

@Data
@Entity
@Table(name = "artical_catigory")
public class ArticalCatigory{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String catigoryName;
    Date createDate;

}
