package cn.ajiehome.common.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.logging.Logger;


@Slf4j
@Configuration
public class GoWebMvcConfigurerAdapter implements WebMvcConfigurer {
    private static Logger logger = Logger.getLogger("GoWebMvcConfigurerAdapter.class");
    @Value("${local.image.url}")
    private String localImageUrl;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File file = new File(localImageUrl);
        System.out.println("文件夹是否存在=>："+localImageUrl+"=>"+file.exists());
        if (!file.exists()){
            boolean mkdirs = file.mkdirs();
            log.info("图片文件夹创建=>"+mkdirs);
        }
        registry.addResourceHandler("/image/**").addResourceLocations("file:/"+localImageUrl);
    }
}
