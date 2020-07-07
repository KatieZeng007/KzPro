package com.kz.jdk.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author KatieZ
 * @Date Created in 15:19  15:19
 */
@Data
@AllArgsConstructor
public class Apple implements Serializable {

    private String tag;

    private Integer weight;
}
