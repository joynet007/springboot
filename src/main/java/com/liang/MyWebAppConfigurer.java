package com.liang;
import com.liang.LiangInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by liang on 2017/7/10.
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
//      registry.addInterceptor(new LiangInterceptor()).addPathPatterns("/ruankao").excludePathPatterns("/**/*.html");
        registry.addInterceptor(new LiangInterceptor());
        super.addInterceptors(registry);
    }
}
