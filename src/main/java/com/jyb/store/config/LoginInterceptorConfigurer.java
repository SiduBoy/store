package com.jyb.store.config;

import com.jyb.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.ArrayList;
import java.util.List;

@Configuration//加载当前拦截并注册
//处理拦截器的注册
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();

        //配置白名单:存放在一个list集合中
        List<String> patterns = new ArrayList<String>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");//菜单
        patterns.add("/users/login");
        patterns.add("/users/reg");
        patterns.add("/districts/**");
        patterns.add("/products/**");

        //完成拦截注册，向注册器中添加拦截器
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")//表示要拦截的URL是什么
                .excludePathPatterns(patterns);// 白名单
    }
}
