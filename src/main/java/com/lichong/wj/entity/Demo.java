package com.lichong.wj.entity;

import com.lichong.wj.entity.base.IBaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "demo_table")
@ToString
public class Demo implements IBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String name;
    String address;
    Date regDate;

}
