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
		// ��ȡ�������
		String userName = req.getParameter("userName");
		String passwd = req.getParameter("passwd");

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		HttpSession session = req.getSession();
		// ֻ���û�����������ͬ�����¼�ɹ�
		if (userName.equals(passwd)) {
			// ����cookie����
			Cookie userInfoCookie = new Cookie("userInfo", userName + ":" + passwd);

			// �������Ҫ���������޷������� ��������� .��ͷ������.qiandu.com
			// �ȸ�������Զ����������.
			userInfoCookie.setDomain("qiandu.com");

			// ���ظ�����������������cookie��Ϣ
			resp.addCookie(userInfoCookie);

			session.setAttribute("userName", userName + "����¼�ɹ�");

		} else {
			session.setAttribute("userName", userName + "����¼ʧ��");
		}

		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}