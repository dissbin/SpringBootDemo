package com.AIfntech.service.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.AIfntech.service.pojo.User;


public class LoginFilter extends HandlerInterceptorAdapter{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("request请求地址："+request.getServletPath());
		System.out.println("request URL:"+request.getRequestURI());
		HttpSession session = request.getSession(true);
		String username = (String)session.getAttribute("username");
		System.out.println("拦截器获取的用户名:"+username);
		if(username == null) {
			response.sendRedirect("/prepareLogin");
		}
		else {
			System.out.println("设置的username值:"+(String)session.getAttribute("username"));
			return true;
		}
		return true;
	}
	
}
