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
		System.out.println("��ʼ������");
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
		if(userInfo == null){ // û��¼
			if(cookies != null){ // ��cookie
				for(Cookie cookie : cookies){
					if("userInfo".equals(cookie.getName())){
						String[] value = cookie.getValue().split(":");
						String userName = value[0];
						String passwd = value[1];
						// ֻ���û�����������ͬ�����¼�ɹ�
						if(userName.equals(passwd)){
							// ����cookie����
							session.setAttribute("userName", userName + "����filter��¼�ɹ�");
						}else {
							session.setAttribute("userName", userName + "����filter��¼ʧ��");
						}
					}
				}
			} else {
				// ����Ӧ����ת����¼ҳ��
			}
		}
		
		chain.doFilter(request, response);
	}
	

	public void destroy() {
		System.out.println("��ʼ������");
	}
}