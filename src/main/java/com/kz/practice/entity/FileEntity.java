package com.kz.practice.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description TODO
 * @Author KatieZ
 * @Date Created in 17:09  17:09
 */
@Data
public class FileEntity {

    private Long id;

    private List<SubFileEntity> subFileEntityList;
}
