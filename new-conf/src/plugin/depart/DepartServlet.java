package plugin.depart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.InterningXmlVisitor;

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
			this.getDepart(request, response);
		}else if(m.equals("saveuser")){//保存用户信息
			this.saveUser(request, response);
		}else if(m.equals("savebatchuser")){//批量保存用户信息
			this.saveDepart(request, response);
		}else if(m.equals("savedepart")){//保存部门信息
			this.saveDepart(request, response);
		}else if(m.equals("deletedu")){//删除用户和部门信息
			this.deletedu(request, response);
		}
	}
	

	public void init() throws ServletException {
		// Put your code here
	}
	public void returnJson(HttpServletRequest request, HttpServletResponse response,ResultBean result)
			throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject r = JSONObject.fromObject(result);
		out.print(r.toString());
		out.flush();
		out.close();
	}
	private int getInt(Object obj){
		int result = 0;
		if(obj==null){
			return 0;
		}
		try{
			result = Integer.parseInt(obj.toString());
		}catch(Exception e){
			
		}
		return result;
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
		int userid = this.getInt(request.getParameter("id"));
		int org = orgid==null?0:Integer.parseInt(orgid);
		ResultBean result = new ResultBean();
		Map<String,Object> data = DepartmentDao.dao.getUserinfo(userid, org);
		if(data==null){
			result.setCode(1);
		}else{
			result.setCode(0);;
			result.setData(data);
		}
		this.returnJson(request, response, result);
	}
	public void getDepart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orgid = request.getParameter("orgid");
		int departid = this.getInt(request.getParameter("id"));
		int org = orgid==null?0:Integer.parseInt(orgid);
		ResultBean result = new ResultBean();
		Map<String,Object> data = DepartmentDao.dao.getDepartinfo(departid, org);
		if(data==null){
			result.setCode(1);
		}else{
			result.setCode(0);;
			result.setData(data);
		}
		this.returnJson(request, response, result);
	}
	public void deletedu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int orgid = this.getInt(request.getParameter("orgid"));
		String dids = request.getParameter("dids");
		String uids = request.getParameter("uids");
		ResultBean result = new ResultBean();
		boolean flag = false;
		if(dids!=null){
			if(DepartmentDao.dao.deleteDepart(dids, orgid)){
				flag = true;
			}
		}
		if(uids!=null){
			if(DepartmentDao.dao.deleteUser(uids, orgid)){
				flag = true;
			}
		}
		result.setCode(flag?0:1);
		this.returnJson(request, response, result);
	}
	public void saveUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = this.getInt(request.getParameter("id"));
		int orgid = this.getInt(request.getSession().getAttribute("orgid"));
		int departid = this.getInt(request.getParameter("departid"));
		int orders = this.getInt(request.getParameter("orders"));
		int role = this.getInt(request.getParameter("role"));
		String account = request.getParameter("account");
		String alias = request.getParameter("alias");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		
		ResultBean result = new ResultBean();
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("id", id);
		data.put("departid", departid);
		data.put("orgid", orgid);
		data.put("orders", orders);
		data.put("role", role);
		data.put("account", account);
		data.put("alias", alias);
		data.put("email", email);
		data.put("phone", phone);
		data.put("password", password);
		result.setCode(DepartmentDao.dao.saveOrUpdateUser(data)?0:1);
		this.returnJson(request, response, result);
	}
	public void saveDepart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = this.getInt(request.getParameter("id"));
		int orgid = this.getInt(request.getSession().getAttribute("orgid"));
		int pid = this.getInt(request.getParameter("pid"));
		int orders = this.getInt(request.getParameter("orders"));
		String name = request.getParameter("name");
		
		ResultBean result = new ResultBean();
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("id", id);
		data.put("pid", pid);
		data.put("orgid", orgid);
		data.put("orders", orders);
		data.put("name", name);
		result.setCode(DepartmentDao.dao.saveOrUpdateDepart(data)?0:1);
		this.returnJson(request, response, result);
	}

}
