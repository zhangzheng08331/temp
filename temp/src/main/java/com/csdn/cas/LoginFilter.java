package com.csdn.cas;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {


	public void init(FilterConfig filterConfig) throws ServletException { 
		System.out.println("初始化方法");
	}
	 

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request ;
		HttpServletResponse resp = (HttpServletResponse) response ;
		Cookie[] cookies = req.getCookies();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = req.getSession();
		Object userInfo = session.getAttribute("userName");
		if(userInfo == null){ // 没登录
			if(cookies != null){ // 有cookie
				for(Cookie cookie : cookies){
					if("userInfo".equals(cookie.getName())){
						String[] value = cookie.getValue().split(":");
						String userName = value[0];
						String passwd = value[1];
						// 只有用户名与密码相同，则登录成功
						if(userName.equals(passwd)){
							// 创建cookie对象
							session.setAttribute("userName", userName + "，从filter登录成功");
						}else {
							session.setAttribute("userName", userName + "，从filter登录失败");
						}
					}
				}
			} else {
				// 这里应该跳转到登录页面
			}
		}
		
		chain.doFilter(request, response);
	}
	

	public void destroy() {
		System.out.println("初始化方法");
	}
}