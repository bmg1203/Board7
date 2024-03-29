package com.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.board.interceptor.LoginCheckInterceptor;

/*
폴더 위치 : WebMvcConfig.java
- Main() 함수가 있는 클래스(Board6Application.java)의 패키지(com.board) 아래에 .config를 생성하여 저장
com.config 용도
- Spring legacy project는 설정을 .xml에 저장
- SpringBoot Project는 설정을 WebMvcConfig.java에 저장
*/

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
		
	@Autowired
	private LoginCheckInterceptor loginCheckInterceptor;
		
	@Override
    public void addInterceptors(InterceptorRegistry registry) {

       System.out.println("okokok");
       registry.addInterceptor( loginCheckInterceptor )
               .addPathPatterns("/**")      // http://localhost:9090/
               .addPathPatterns("/**/*")    //모든 파일에 적용
               .excludePathPatterns("/log*","/css/**", "/img/**", "/js/**"); //제외할 것 
	}
	
	
}
