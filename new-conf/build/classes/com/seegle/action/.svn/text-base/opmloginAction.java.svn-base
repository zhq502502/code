package com.seegle.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class opmloginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String path = "opmlogin.jsp";

    public opmloginAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
        String orgid = request.getParameter("orgid");
        String userid = request.getParameter("userid");
        String userpass = request.getParameter("userpass");
        String type = request.getParameter("type");
        String alias = request.getParameter("alias");
        request.setAttribute("orgid", orgid);
        request.setAttribute("userid", userid);
        request.setAttribute("userpass", userpass);
        request.setAttribute("type", type);
        request.setAttribute("alias", alias);  
        request.getRequestDispatcher(path).forward(request, response);
	}

}
