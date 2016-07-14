package plugin.depart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class DepartServlet extends HttpServlet {

	public DepartServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");	
		
		String m = request.getParameter("m");
		if(m==null){
			
		}else if(m.equals("dutree")){//获取部门和用户组合的tree信息
			this.dutree(request, response);
		}else if(m.equals("getuser")){//获取单个用户信息
			this.getuser(request, response);
		}else if(m.equals("getdepart")){//获取单个部门信息
			
		}else if(m.equals("saveuser")){//保存用户信息
			
		}else if(m.equals("savebatchuser")){//批量保存用户信息
			
		}else if(m.equals("savedepart")){//保存部门信息
			
		}else if(m.equals("deletedu")){//删除用户和部门信息
			
		}
	}
	

	public void init() throws ServletException {
		// Put your code here
	}
	public void returnJson(HttpServletRequest request, HttpServletResponse response,ResultBean result)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject r = JSONObject.fromObject(result);
		out.print(r.toString());
		out.flush();
		out.close();
	}
	public void helloword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	public void dutree(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orgid = request.getParameter("orgid");
		int org = orgid==null?0:Integer.parseInt(orgid);
		List<TreeBean> list = DepartmentDao.dao.getDepartAndUser(org);
		ResultBean result = new ResultBean();
		result.setCode(0);
		result.setData(list);
		this.returnJson(request, response, result);
		
	}
	public void getuser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orgid = request.getParameter("orgid");
		String userid = request.getParameter("id");
		int org = orgid==null?0:Integer.parseInt(orgid);
		ResultBean result = new ResultBean();
		
		
		this.returnJson(request, response, result);
	}

}
