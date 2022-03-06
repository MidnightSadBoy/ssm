package com.hssj.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.hssj.pojo.User;

public class LoginIntercepetor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			return true;
		}
		response.setContentType("text/html;chartset=utf-8");
		response.getWriter().write("您还未登录！请先登录再访问");
		return false;
	}
}
