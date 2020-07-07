package com.kz.practice.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description TODO
 * @Author KatieZ
 * @Date Created in 17:11  17:11
 */
@Data
public class SubFileEntity {

    private Long certId;

    private String name;

    private MultipartFile certFile;

    private MultipartFile xlsFile;

}
