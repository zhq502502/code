package greendog.dwr.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContextFactory;

public class UserLogin extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String userid = request.getParameter("userid");
//		request.getSession().setAttribute("userid", userid);
		request.getRequestDispatcher("/page/dwr/dwrTest.jsp").forward(request, response);
	}

}
