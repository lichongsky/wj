package com.lichong.wj.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ：lichongsky
 * @date ：Created in 2019/7/23 11:25
 */
@Data
@Entity
@Table(name = "pv")
public class Pv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    Date countDate;
    int pv;
    int uid;
}
