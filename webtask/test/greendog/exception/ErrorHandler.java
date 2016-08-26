package greendog.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		Error error = new Error();
		error.setCode(statusCode);
		error.setErrorservlet(servletName);
		error.setErrorurl(requestUri);
		if(throwable==null){
			error.setException(0);
		}else{
			error.setException(1);
			error.setErrormsg(throwable.toString());
			StringBuffer stack = new StringBuffer();
			for(Object st:throwable.getStackTrace()){
				stack.append(st+"<br/>");
			}
			error.setStack(stack.toString());
		}
//		String url = request.getQueryString();
//		error.setErrorurl(requestUri+"?"+url);
		request.setAttribute("error", error);
		request.getRequestDispatcher("/page/error/error.jsp").forward(request, response);
	}

}
