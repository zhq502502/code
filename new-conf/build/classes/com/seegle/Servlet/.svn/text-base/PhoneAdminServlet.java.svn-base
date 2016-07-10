package com.seegle.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seegle.data.PhoneUserOperation;

public class PhoneAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PhoneAdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String webSiteUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		if(request.getSession().getAttribute("token")==null){
			response.sendRedirect(webSiteUrl+"/Login.jsp");
		}
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String operation = request.getParameter("operation");
		//String orgtype = request.getSession().getAttribute("orgtype");
		String orgid = request.getSession().getAttribute("orgid").toString();
		String token = request.getSession().getAttribute("token").toString();
		String userids = request.getParameter("account");
		int flag = -1;
		PhoneUserOperation  puo =  new  PhoneUserOperation(orgid);
		if(operation.equals("addPhoneAdmin")){
			String type = request.getParameter("type");
			flag = puo.addPhoneAdmin(orgid, token, userids, type);			
		}
		if(operation.equals("delPhoneAdmin")){
			flag = puo.delPhoneAdmin(orgid, token, userids);
		}
		out.print(flag);
	}

}
