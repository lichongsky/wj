package com.lichong.wj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles_user")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class RolesUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    /**
     * 角色id
     */
    int rid;
    /**
     * 用户id
     */
    int uid;
}
