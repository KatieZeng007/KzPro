package com.kz.practice.config;

import com.kz.practice.PracticeApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ Author     ：KatieZ
 * @ Date       ：Created in 10:30 2019/11/4
 * @ Description：Swagger接口文档配置,进行接口文档分组
 * @ Modified By：KatieZ
 * @Version: V1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket restPracticeApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("kz的Demo服务")
                .apiInfo(apiInfo("kz的Demo服务RESTful APIs", PracticeApplication.CURRENT_VERSION))
                .useDefaultResponseMessages(true)

                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kz.practice.controller"))
                .paths(PathSelectors.regex("/*.*"))
                .build();
    }

    /**
     * 基本的API信息
     * @param title
     * @param version
     * @return
     */
    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfoBuilder()
                .title(title)
                .description("kz的Demo服务当前API版本" + PracticeApplication.CURRENT_VERSION)
                .version(PracticeApplication.CURRENT_VERSION)
                .termsOfServiceUrl("http://localhost:8765/")
                .contact(new Contact("hj", "http://localhost:8765/", "2269157059@qq.com"))
                .version(version)
                .build();
    }

}
