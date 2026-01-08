package cn.navg.laboratory.config;

import cn.navg.laboratory.interceptor.CheckTokenInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//拦截器配置类
@Component
public class InterceptorConfig implements WebMvcConfigurer {
    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CheckTokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/user/verifyToken","/email");
    }
}
