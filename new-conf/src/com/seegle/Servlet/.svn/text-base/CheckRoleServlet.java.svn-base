package com.seegle.Servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import com.seegle.data.SQLOperation;
import com.seegle.util.SeegleLog;


public class CheckRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public CheckRoleServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();		
		HttpSession session = request.getSession();
//		SeegleLog.getInstance().getLog(this.getClass(), request.getSession().getAttribute("orgid").toString()).debug("debug in CheckRoleServlet");

		String account = session.getAttribute("userid").toString();
		String orgid = session.getAttribute("orgid").toString();
		String role = "-2";
		SQLOperation so = new SQLOperation();		
		role = so.getUserrole(orgid, account); //获取用户权限

		out.print(role);	
	}

}
