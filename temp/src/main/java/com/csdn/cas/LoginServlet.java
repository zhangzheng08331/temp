package com.csdn.cas;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取请求参数
		String userName = req.getParameter("userName");
		String passwd = req.getParameter("passwd");

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		HttpSession session = req.getSession();
		// 只有用户名与密码相同，则登录成功
		if (userName.equals(passwd)) {
			// 创建cookie对象
			Cookie userInfoCookie = new Cookie("userInfo", userName + ":" + passwd);

			// 这里很重要，不设置无法夸子域 这里最好以 .开头，例如.qiandu.com
			// 谷歌浏览器自动给他添加了.
			userInfoCookie.setDomain("qiandu.com");

			// 返回给浏览器的数据中添加cookie信息
			resp.addCookie(userInfoCookie);

			session.setAttribute("userName", userName + "，登录成功");

		} else {
			session.setAttribute("userName", userName + "，登录失败");
		}

		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}