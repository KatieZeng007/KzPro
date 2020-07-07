package com.kz.practice.entity;

import lombok.Data;

/**
 * @Description 树节点
 * @Author KatieZ
 * @Date Created in 10:20  10:20
 */
@Data
public class Tree {

    private Long id;

    private String name;

    private Long pid;

    private Integer level;

}
