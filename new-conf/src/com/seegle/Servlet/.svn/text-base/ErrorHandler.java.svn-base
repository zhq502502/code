package com.seegle.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seegle.beans.WebError;
import com.seegle.util.SeegleLog;

public class ErrorHandler extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
		String servletName = (String)request.getAttribute("javax.servlet.error.servlet_name");
		String requestUri = (String)request.getAttribute("javax.servlet.error.request_uri");
		String queryString = request.getQueryString();
		WebError error = new WebError();
		error.setCode(statusCode);
		error.setErrorservlet(servletName);
		error.setErrorurl(requestUri+(!(queryString==null||queryString.equals(""))?"?"+queryString:""));
		if(throwable==null){
			error.setException(1);
		}else{
			error.setException(0);
			error.setErrormsg(throwable.toString());
			StringBuffer stack = new StringBuffer();
			for(Object st:throwable.getStackTrace()){
				stack.append(st+"\n");
			}
			error.setStack(stack.toString());
		}
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String callback = request.getParameter("callback");
		SeegleLog.getInstance().getLog(this.getClass(),"ERROR").error("code:"+error.getCode()+",msg:"+error.getErrormsg()+",errorUrl:"+error.getErrorurl());
		request.setAttribute("error", error);
		request.getRequestDispatcher("/Error.jsp").forward(request, response);
	}

}
