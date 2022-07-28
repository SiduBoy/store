/*
package com.jyb.store.config;

import com.jyb.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
*/
/*处理拦截器的注册*//*


public class LoginInterceptorConfigurer implements WebMvcConfigurer {


*/
/*配置拦截器*//*


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();
        //配置白名单:存放在一个list集合中
        List<String> pass = new ArrayList<>();
        pass.add("/bootstrap3/**");
        pass.add("/css/**");
        pass.add("/images/**");
        pass.add("/js/**");
        pass.add("/web/register.html");
        pass.add("/web/login.html");
        pass.add("/web/index.html");
        pass.add("/web/product.html");//菜单
        pass.add("/user/login");
        pass.add("/user/reg");

        //完成拦截注册，向注册器中添加拦截器
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")//表示要拦截的URL是什么
                .excludePathPatterns(pass);// 白名单
    }
}
*/
