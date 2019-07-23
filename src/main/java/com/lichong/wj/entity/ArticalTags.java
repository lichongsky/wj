package com.lichong.wj.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author ：lichongsky
 * @date ：Created in 2019/7/23 11:23
 */
@Data
@Entity
@Table(name = "artical_tags")
public class ArticalTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    int articalId;
    int articalCatigoryId;
}
