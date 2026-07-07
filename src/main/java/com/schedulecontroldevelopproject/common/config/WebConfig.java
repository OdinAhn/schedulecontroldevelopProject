package com.schedulecontroldevelopproject.common.config;

import com.schedulecontroldevelopproject.common.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**") // 모든 경로에 적용
                .excludePathPatterns(   // 로그인 없이도 접근 가능해야 하는 경로는 제외
                        "/login",
                        "/logout",
                        "/users" // 회원가입은 로그인 전에 해야 하니 제외
                );
    }
}
