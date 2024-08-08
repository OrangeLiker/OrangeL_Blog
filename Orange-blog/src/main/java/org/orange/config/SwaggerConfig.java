package org.orange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.parameters.P;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @BelongsProject: Orange_Blog
 * @ClassName SwaggerConfig
 * @Description SwaggerDoc配置类
 * @Author WangZJ0908
 * @Date 2024/8/8
 * @Version: 1.0
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.orange.controller"))
                .build();
    }

    private ApiInfo apiInfo(){
        Contact contact=new Contact("Orange_Blog","http://orangeliker.top","2814964382@qq.com");
        return new ApiInfoBuilder()
                .title("Orange_Blog接口文档")
                .description("关于Orange_Blog🍊")
                .contact(contact)
                .version("1.1.0")
                .build();
    }
}
