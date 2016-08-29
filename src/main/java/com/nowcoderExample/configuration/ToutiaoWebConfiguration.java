package com.nowcoderExample.configuration;

import com.nowcoderExample.interceptor.LoginRequiredInterceptor;
import com.nowcoderExample.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
@Component
public class ToutiaoWebConfiguration extends WebMvcConfigurerAdapter{
    @Autowired
    PassportInterceptor passportInterceptor;

    @Autowired
    LoginRequiredInterceptor loginRequiredInterceptor;

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor);//处理全局
        registry.addInterceptor(loginRequiredInterceptor).addPathPatterns("/setting*");
        super.addInterceptors(registry);

    }
}
