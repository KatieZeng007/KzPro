package com.kz.practice.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.zxing.WriterException;
import com.itextpdf.text.DocumentException;
import com.kz.practice.entity.FileEntity;
import com.kz.practice.entity.SubFileEntity;
import com.kz.practice.util.Response;
import com.kz.practice.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @Description TODO
 * @Author KatieZ
 * @Date Created in 17:26  17:26
 */
@Api(tags = {"Example"})
@RestController
@RequestMapping("/kz/{version}/file")
@Slf4j
public class FileController {

    @PostMapping("/much")
    public void projectExport(FileEntity fileEntity){
        for(SubFileEntity subFileEntity:fileEntity.getSubFileEntityList()){
            log.info(String.format("证照：%s",subFileEntity.getName()));
            log.info("证照名称: "+subFileEntity.getCertFile().getOriginalFilename());
            log.info("明细名称: "+subFileEntity.getXlsFile().getOriginalFilename());
        }
    }

    @ApiOperation("初始化菜单")
    @PostMapping("/menu/init")
    public void menuFromJson(@RequestBody MultipartFile file) {
        String str = null;
        StringBuffer jsonStr = new StringBuffer();
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((str = bufferedReader.readLine()) != null){
                jsonStr.append(str);
            }
            JsonObject jsonObject = new JsonParser().parse(jsonStr.toString()).getAsJsonObject();
            log.info(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("初始化菜单")
    @GetMapping("/http/get")
    public Response<String> get() throws IOException {
        String str = null;
        FileInputStream inputStream = new FileInputStream(new File("D:\\programmer files\\sqlite\\supplier.txt"));
        StringBuffer json = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while((str = bufferedReader.readLine()) != null){
            json.append(str);
        }
        return ResponseUtils.returnSuccess(json.toString());
    }

}
