	package com.prakash.TodoList.utils;

	import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
	@Component
	public class TodoListInterceptor implements HandlerInterceptor {
	
	 @Autowired
	  private JwtTokenUtil jwtTokenUtil;
	
	 @Override
	 public boolean preHandle(HttpServletRequest request,HttpServletResponse  response, Object handler)throws Exception{
	    		
			System.out.println("Inside pre handle");
			String accessToken = request.getHeader("Authorization");

	        System.out.println("End Point is: " + request.getRequestURI());
	        System.out.println("Method is: " + request.getMethod());

	        System.out.println("Role is: " + jwtTokenUtil.getRoleFromToken(accessToken));

	        String emailId = jwtTokenUtil.getUsernameFromToken(accessToken);

	        if (emailId == null || emailId.trim().length() == 0) {
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            return false;
	        }

	        User userDetails = new User(emailId,"", new ArrayList<>());
	        if (jwtTokenUtil.validateToken(accessToken, userDetails)) {
	            return true;
	        }
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	       
	        return false;
	    }
		@Override
		public void postHandle(HttpServletRequest request,HttpServletResponse  response, 
			Object handler, ModelAndView modelAndView)throws Exception{
			System.out.println("Inside post handle");
	        
	    }
	
		@Override
		public void afterCompletion(HttpServletRequest request,HttpServletResponse  response, 
			Object handler, Exception e)throws Exception{
			System.out.println("Inside after completion");
		
		}
	
	

}
