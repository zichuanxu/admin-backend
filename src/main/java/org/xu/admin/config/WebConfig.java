package org.xu.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xu.admin.interceptor.AuthInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private AuthInterceptor authInterceptor;


    @Value("${file.upload.path}")
    private String uploadPath;

    @Value("${file.upload.access-path}")
    private String accessPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 1. 获取访问路径前缀，例如 "/files/**"
        // 如果你的 accessPath 是 "/files/"，需要拼接 "**"
        String pathPattern = accessPath.endsWith("/") ? accessPath + "**" : accessPath + "/**";

        // 2. 获取本地绝对路径
        // 关键点：Spring Boot 的 addResourceLocations 需要 "file:" 前缀来标识本地文件系统
        // 关键点：目录路径必须以 "/" 结尾
        String localPath = "file:" + uploadPath;
        if (!localPath.endsWith("/")) {
            localPath += "/";
        }

        // 3. 建立映射关系
        // 也就是：当访问 http://localhost:8080/files/xxx.jpg -> 映射到 file:/Users/zichuanxu/.../upload/xxx.jpg
        registry.addResourceHandler(pathPattern)
                .addResourceLocations(localPath);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/user/register"); // 排除登录注册
    }
}