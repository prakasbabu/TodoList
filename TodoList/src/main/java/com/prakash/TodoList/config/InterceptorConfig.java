package com.prakash.TodoList.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.prakash.TodoList.utils.TodoListInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	  @Autowired
	    private TodoListInterceptor myInterceptor;

	    @Override
	    public void addInterceptors(InterceptorRegistry registry){
	          registry.addInterceptor(myInterceptor).addPathPatterns("/api/**");
	          registry.addInterceptor(myInterceptor).excludePathPatterns("/api/customer-login");
	          registry.addInterceptor(myInterceptor).excludePathPatterns("/api/admin-login");
	    }
}
